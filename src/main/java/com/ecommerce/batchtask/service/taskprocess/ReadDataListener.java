package com.ecommerce.batchtask.service.taskprocess;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadDataListener implements ReadListener {
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        log.info("invoke");

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
