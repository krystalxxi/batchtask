package com.ecommerce.batchtask.service.taskprocess;

import com.ecommerce.batchtask.service.taskprocess.bo.ExportFile;

/**
 * 导出文件
 */
public interface ExportFileService {
    /**
     * 导出
     * @param exportFile
     */
    void export(ExportFile exportFile);
}
