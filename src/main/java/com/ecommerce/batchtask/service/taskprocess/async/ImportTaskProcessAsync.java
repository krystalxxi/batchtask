package com.ecommerce.batchtask.service.taskprocess.async;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@EnableAsync
public class ImportTaskProcessAsync {


    @Async("importTaskExecutor")
    public CompletableFuture<Boolean> batchImport(){

        return null;
    }
}
