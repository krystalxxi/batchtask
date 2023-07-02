package com.ecommerce.batchtask.service.taskprocess.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@Builder
public class ImportFile {
    private volatile Queue<List<List<Object>>> queue = new LinkedBlockingQueue<>();

    /**
     * 导入文件地址
     */
    private String importFilePath;

    /**
     * 文件类型
     */
    private Integer fileType;

    private Object targetBean;

    private Class target;

    public void addData(List<List<Object>> data) throws Exception {
        queue.offer(data);
    }
}
