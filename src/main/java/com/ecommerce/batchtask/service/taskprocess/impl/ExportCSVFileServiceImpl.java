package com.ecommerce.batchtask.service.taskprocess.impl;

import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.ExportFileService;
import com.ecommerce.batchtask.service.taskprocess.bo.ExportFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FileType(type = FileTypeEnum.CSV)
@Slf4j
public class ExportCSVFileServiceImpl implements ExportFileService {
    public void export(ExportFile exportFile) {

    }
}
