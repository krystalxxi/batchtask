package com.ecommerce.batchtask.service.taskprocess.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.ExportFileService;
import com.ecommerce.batchtask.service.taskprocess.bo.ExportFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

@Service
@Slf4j
@FileType(type = FileTypeEnum.EXCEL)
public class ExportExcelFileServiceImpl implements ExportFileService {

    @Override
    public void export(ExportFile exportFile) {
        // 获取模板文件，如果文件不存在则创建
        FileUrlResource fileUrlResource = null;
        try {
            fileUrlResource = new FileUrlResource(exportFile.getTemplate());
            if (!exportFile.getFile().exists()) {
                exportFile.getFile().createNewFile();
            }
            InputStream inputStream = fileUrlResource.getInputStream();
            OutputStream outputStream = new FileOutputStream(exportFile.getFile(), true);

            // 写文件
            ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
            while (!exportFile.isEmpty()) {
                excelWriter.write(exportFile.getData(), writeSheet);
            }
            excelWriter.finish();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
