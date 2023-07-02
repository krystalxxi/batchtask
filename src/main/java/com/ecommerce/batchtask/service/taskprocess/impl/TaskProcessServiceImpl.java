package com.ecommerce.batchtask.service.taskprocess.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ecommerce.basicplatform.param.PageParam;
import com.ecommerce.batchtask.common.enums.TaskProcessStateEnum;
import com.ecommerce.batchtask.common.enums.TaskTypeEnum;
import com.ecommerce.batchtask.common.exceptions.BatchTaskProcessBizException;
import com.ecommerce.batchtask.infrastructure.dao.TaskProcessDao;
import com.ecommerce.batchtask.infrastructure.persistence.assembler.DataMapper;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskProcess;
import com.ecommerce.batchtask.service.taskprocess.ExportTaskProcessService;
import com.ecommerce.batchtask.service.taskprocess.ImportTaskProcessService;
import com.ecommerce.batchtask.service.taskprocess.TaskProcessService;
import com.ecommerce.batchtask.service.taskprocess.bo.CreateTaskBo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskBo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskProcessBo;
import com.ecommerce.batchtask.service.taskprocess.util.TaskProcessUtil;
import com.ecommerce.batchtask.service.tasktemplate.TaskTemplateService;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskProcessServiceImpl implements TaskProcessService {
    @Autowired
    TaskTemplateService taskTemplateService;
    @Autowired
    TaskProcessDao taskProcessDao;
    @Autowired
    ExportTaskProcessService exportTaskProcessService;

    @Autowired
    ImportTaskProcessService importTaskProcessService;

    /**
     * 创建任务
     *
     * @param createTaskBo
     * @return
     */
    @Override
    public boolean createTask(CreateTaskBo createTaskBo) throws Exception {
        // 获取任务模板
        TaskTemplateBo taskTemplateBo = taskTemplateService.getTaskTemplate(createTaskBo.getTaskName(), createTaskBo.getTaskType());
        if (null == taskTemplateBo) {
            throw new BatchTaskProcessBizException("模板不存在,{}", createTaskBo.getTaskName());
        }
        // 保存任务
        TaskProcess taskProcess = new TaskProcess();
        taskProcess.setTaskId(taskTemplateBo.getId());
        taskProcess.setState(TaskProcessStateEnum.PROCESSING.getCode());
        taskProcess.setTaskType(createTaskBo.getTaskType());
        taskProcessDao.save(taskProcess);

        TaskBo taskBo = TaskBo.builder()
                .taskId(taskProcess.getId())
                .taskTemplateBo(taskTemplateBo)
                .build();
        if (TaskTypeEnum.EXPORT.getCode() == createTaskBo.getTaskType().byteValue()) {
            // process export
            taskBo.setData(createTaskBo.getData());
            taskBo.setFileType(TaskProcessUtil.getFileType(taskTemplateBo.getTemplateName()));
            exportTaskProcessService.exportAsync(taskBo);
        } else if (TaskTypeEnum.IMPORT.getCode() == createTaskBo.getTaskType().byteValue()) {
            // process import
            taskBo.setFileType(TaskProcessUtil.getFileType(createTaskBo.getImportFilePath()));
            taskBo.setImportFilePath(createTaskBo.getImportFilePath());
            importTaskProcessService.importAsync(taskBo);
        }
        return Boolean.TRUE;
    }

    /**
     * 查询任务列表
     *
     * @param param
     * @return
     */
    public List<TaskProcessBo> queryTaskList(PageParam param) {
        Wrapper wrapper = new QueryWrapper<>();
        List<TaskProcess> taskProcesses = taskProcessDao.list(wrapper);
        return DataMapper.INSTANCE.toTaskTemplateBoList(taskProcesses);
    }

}
