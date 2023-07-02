package com.ecommerce.batchtask.service.taskprocess;

import com.ecommerce.batchtask.service.taskprocess.bo.ImportFile;

/**
 * 导入文件服务
 */
public interface ImportFileService {

    /**
     * 导入
     * @return
     */
    void doImport(ImportFile importFile) throws Exception;
}
