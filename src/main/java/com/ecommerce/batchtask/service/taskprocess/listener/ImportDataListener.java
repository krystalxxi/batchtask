package com.ecommerce.batchtask.service.taskprocess.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ecommerce.batchtask.service.taskprocess.bo.ImportVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ImportDataListener extends AnalysisEventListener<ImportVo> {
    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 20;
    @Getter
    List<ImportVo> list = new ArrayList<>(BATCH_COUNT);

    @Override
    public void invoke(ImportVo vo, AnalysisContext analysisContext) {
        list.add(vo);
        if (list.size() >= BATCH_COUNT) {
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
