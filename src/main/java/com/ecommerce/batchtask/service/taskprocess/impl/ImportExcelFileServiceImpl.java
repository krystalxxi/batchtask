package com.ecommerce.batchtask.service.taskprocess.impl;

import com.alibaba.excel.EasyExcel;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.common.exceptions.BatchTaskProcessBizException;
import com.ecommerce.batchtask.service.taskprocess.ImportFileService;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.bo.ImportFile;
import com.ecommerce.batchtask.service.taskprocess.listener.ImportDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

@Service
@Slf4j
@FileType(type = FileTypeEnum.EXCEL)
public class ImportExcelFileServiceImpl implements ImportFileService {
    @Override
    public void doImport(ImportFile importFile) throws Exception{
        FileUrlResource fileUrlResource;
        // 获取导入文件
        try {
            fileUrlResource = new FileUrlResource(importFile.getImportFilePath());
        } catch (MalformedURLException e) {
            throw new BatchTaskProcessBizException("导入文件不存在");
        }
        try (InputStream inputStream = fileUrlResource.getInputStream();) {
          List<Object> objectList =  EasyExcel.read(inputStream, importFile.getTarget(),new ImportDataListener()).doReadAllSync();
          log.info("{}",objectList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
