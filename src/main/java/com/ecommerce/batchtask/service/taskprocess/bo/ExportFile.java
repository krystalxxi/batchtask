package com.ecommerce.batchtask.service.taskprocess.bo;

import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class ExportFile {
    private volatile Queue<List<List<Object>>> queue = new LinkedBlockingQueue<>();

    /**
     * 导出文件
     */
    private File file;

    /**
     * 导出模板
     */
    private String template;

    public ExportFile(File file, String template) {
        this.file = file;
        this.template = template;
    }

    public void addData(List<List<Object>> data) throws Exception {
        queue.offer(data);
    }

    public List<List<Object>> getData(){
        return queue.poll();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

}
