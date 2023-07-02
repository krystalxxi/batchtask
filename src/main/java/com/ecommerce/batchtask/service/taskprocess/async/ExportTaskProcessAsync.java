package com.ecommerce.batchtask.service.taskprocess.async;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecommerce.batchtask.service.taskprocess.bo.ExportFile;
import com.ecommerce.batchtask.service.taskprocess.bo.FileHead;
import com.ecommerce.batchtask.service.taskprocess.util.DubboSupportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 任务异步处理线程池定义
 */
@Slf4j
@Component
@EnableAsync
public class ExportTaskProcessAsync {
    @Async("exportTaskExecutor")
    public CompletableFuture<String> getExportData(URL url, ExportFile exportFile, List<FileHead> headVoList) throws Exception {
        log.info("开始执行导出任务:{}", url);
        JSONObject jsonObject = DubboSupportUtil.invoke(url);
        if (null != jsonObject && jsonObject.containsKey("data")) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<List<Object>> data = convertExportData(headVoList, jsonArray);
            exportFile.addData(data);
        }
        return CompletableFuture.completedFuture("导出任务完成");
    }

    private List<List<Object>> convertExportData(List<FileHead> headVoList, JSONArray data) {
        List<List<Object>> exportData = new ArrayList<>();
        if (null == headVoList || null == data) {
            return exportData;
        }
        List<Object> objectInfos;
        for (int i = 0; i < data.size(); i++) {
            objectInfos = new ArrayList<>();
            JSONObject jsonObject = (JSONObject) data.get(i);
            for (FileHead head : headVoList) {
                objectInfos.add(jsonObject.get(head.getKey()));
            }
            exportData.add(objectInfos);
        }
        return exportData;
    }
}
