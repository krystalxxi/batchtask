package com.ecommerce.batchtask.service.taskprocess.bo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * 文件头
 */
@Data
public class FileHead implements Comparable<FileHead>{
    /**
     * 字段名
     */
    private String key;

    /**
     * 字段序号
     */
    private Integer index;

    /**
     * 字段类型
     */
    private String field;

    @Override
    public int compareTo(@NotNull FileHead o) {
        return 0;
    }
}
