package com.ecommerce.batchtask.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskTemplateDto implements Serializable {
    private Integer id;
    private Byte taskType;
    private String taskName;
    private String invokeService;
    private String invokeServiceVersion;
    private String invokeMethod;
    private String inputArguments;
    private String outputArgument;
    private String templatePath;
    private Byte invokeType;
    private String templateContent;
    private String templateName;
}
