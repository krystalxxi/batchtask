package com.ecommerce.batchtask.service.taskprocess;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.infrastructure.dao.TaskProcessDao;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskProcess;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.async.ExportTaskProcessAsync;
import com.ecommerce.batchtask.service.taskprocess.bo.ExportFile;
import com.ecommerce.batchtask.service.taskprocess.bo.FileHead;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskBo;
import com.ecommerce.batchtask.service.taskprocess.util.DubboSupportUtil;
import com.ecommerce.batchtask.service.taskprocess.util.TaskProcessUtil;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.dubbo.common.URL;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class ExportTaskProcessService {
    @Autowired
    private ExportTaskProcessAsync exportAsyncTask;
    @Resource
    private List<ExportFileService> exportFileServices;
    @Autowired
    private TaskProcessDao taskProcessDao;
    private Map<FileTypeEnum, ExportFileService> exportTaskProcessServiceMap = Maps.newHashMap();

    @Value("${task.export.path}")
    private String exportFilePath;
    final Integer MAX_PAGE_SIZE = 100;

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(exportFileServices)) {
            return;
        }
        exportFileServices.forEach(exportFileService -> {
            FileType fileType = AopUtils.getTargetClass(exportFileService).getAnnotation(FileType.class);
            exportTaskProcessServiceMap.put(fileType.type(), exportFileService);
        });
    }

    /**
     * 异步导出
     * @param exportTask
     * @throws Exception
     */
    public void exportAsync(TaskBo exportTask) throws Exception {

        // 分解任务，异步调用
        List<FileHead> headVoList = TaskProcessUtil.parseTemplateContent(exportTask.getTaskTemplateBo().getTemplateContent());
        URL url = URL.valueOf(exportTask.getTaskTemplateBo().formatUrl(exportTask.getData()));
        JSONObject jsonObject = DubboSupportUtil.invoke(url);
        if (null != jsonObject && jsonObject.containsKey("totalCount")) {
            File exportFile = getExportPath(exportTask.getTaskTemplateBo().getTemplateName());
            String templatePath = exportTask.getTaskTemplateBo().getTemplatePath() + exportTask.getTaskTemplateBo().getTemplateName();
            ExportFile excelFile = new ExportFile(exportFile, templatePath);

            long total = jsonObject.getLong("totalCount");
            exportTask.setPageSize(MAX_PAGE_SIZE);
            List<CompletableFuture> resultList = new ArrayList<>();
            if (total > MAX_PAGE_SIZE) {
                // 分批查询
                int count = (int) Math.ceil(total * 1.0 / MAX_PAGE_SIZE);
                for (int i = 1; i <= count; i++) {
                    exportTask.setPageNo(i);
//                    param.setData(param.getData());
                    url = URL.valueOf(exportTask.getTaskTemplateBo().formatUrl(exportTask.getData()));
                    resultList.add(exportAsyncTask.getExportData(url, excelFile, headVoList));
                }
            } else {
                url = URL.valueOf(exportTask.getTaskTemplateBo().formatUrl(exportTask.getData()));
                resultList.add(exportAsyncTask.getExportData(url, excelFile, headVoList));
            }

            // 执行导出
            ExportFileService exportFileService = exportTaskProcessServiceMap.get(exportTask.getFileType());
            if (null == exportFileService) {
                return;
            }
            exportFileService.export(excelFile);

            CompletableFuture.allOf(resultList.toArray(new CompletableFuture[resultList.size()])).join();
            finishTask(exportTask.getTaskId(), excelFile.getFile().getPath());
        }
    }

    private File getExportPath(String templateName) {
        String fileName = exportFilePath + System.currentTimeMillis() + "_" + templateName;
        File file = new File(fileName);
        return file;
    }


    /**
     * 任务完成更新状态及下载文件地址
     *
     * @param id
     * @param exportPath
     */
    private void finishTask(long id, String exportPath) {
        UpdateWrapper<TaskProcess> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("state", "2");
        updateWrapper.set("export_url", exportPath);
        taskProcessDao.update(updateWrapper);
    }
}
