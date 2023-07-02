package com.ecommerce.batchtask.controller.dto;

import com.ecommerce.basicplatform.param.PageParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTaskDto extends PageParam {
    /**
     * 任务名称
     */
    @NotBlank
    private String taskName;
    /**
     * 任务类型
     */
    @NotBlank
    private Byte taskType;

    /**
     * 页面传参 json格式 导出时用
     */
    private String data;

    /**
     * 导入文件地址
     */
    private String importFilePath;
}
