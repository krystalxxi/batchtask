package com.ecommerce.batchtask.service.tasktemplate.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.infrastructure.dao.TaskTemplateDao;
import com.ecommerce.batchtask.infrastructure.persistence.assembler.DataMapper;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskTemplate;
import com.ecommerce.batchtask.service.tasktemplate.TaskTemplateService;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaskTemplateServiceImpl implements TaskTemplateService {
    @Autowired
    private TaskTemplateDao taskTemplateDao;

    /**
     * 模板文件地址
     */
    @Value("${task.template.path}")
    private String templatePath;

    @Override
    public void createTaskTemplate(TaskTemplateBo taskTemplateBo) {
        taskTemplateBo.setTemplatePath(templatePath);
        if (taskTemplateBo.getTemplateName().endsWith(".xlsx")) {
            taskTemplateBo.setTemplateFileType(FileTypeEnum.EXCEL.getCode());
        } else if (taskTemplateBo.getTemplateName().endsWith(".csv")) {
            taskTemplateBo.setTemplateFileType(FileTypeEnum.CSV.getCode());
        }
        taskTemplateDao.save(DataMapper.INSTANCE.toTaskTemplate(taskTemplateBo));
    }

    // todo 增加缓存处理
    @Override
    public TaskTemplateBo getTaskTemplate(String taskName, Byte taskType){
        QueryWrapper<TaskTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        queryWrapper.eq("task_type", taskType);
        TaskTemplate taskTemplate = taskTemplateDao.getOne(queryWrapper);
        TaskTemplateBo taskTemplateBo = null;
        if (null != taskTemplate) {
            taskTemplateBo = DataMapper.INSTANCE.toTaskTemplateBo(taskTemplate);
        }
        return taskTemplateBo;
    }
}
