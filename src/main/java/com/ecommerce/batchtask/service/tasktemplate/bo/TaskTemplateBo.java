package com.ecommerce.batchtask.service.tasktemplate.bo;

import lombok.Data;

@Data
public class TaskTemplateBo {
    private int id;
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
    private Byte templateFileType;


    public String formatUrl(String param) {
        StringBuilder stringBuilder = new StringBuilder("dubbo://localhost:80?type=dubbo")
                .append("&invokeService=").append(this.getInvokeService())
                .append("&version=").append(this.getInvokeServiceVersion())
                .append("&invokeMethod=").append(this.getInvokeMethod())
                .append("&inputArguments=").append(this.getInputArguments())
                .append("&template=").append(this.getTemplatePath()+this.getTemplateName())
//                .append("&templateContent=").append(this.getTemplateContent())
                .append("&param=").append(param);
        return stringBuilder.toString();
    }
}
