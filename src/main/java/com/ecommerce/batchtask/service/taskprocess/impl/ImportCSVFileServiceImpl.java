package com.ecommerce.batchtask.service.taskprocess.impl;

import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.ImportFileService;
import com.ecommerce.batchtask.service.taskprocess.bo.ImportFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FileType(type = FileTypeEnum.CSV)
@Slf4j
public class ImportCSVFileServiceImpl implements ImportFileService {
    @Override
    public void doImport(ImportFile importFile) throws Exception {
    }
}
