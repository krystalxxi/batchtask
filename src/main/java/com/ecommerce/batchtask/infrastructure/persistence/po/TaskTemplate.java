package com.ecommerce.batchtask.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "task_template")
public class TaskTemplate {
    private long id;
    @TableField(value = "task_type")
    private Byte taskType;
    @TableField(value = "task_name")
    private String taskName;
    @TableField(value = "invoke_service")
    private String invokeService;
    @TableField(value = "invoke_service_version")
    private String invokeServiceVersion;
    @TableField(value = "invoke_method")
    private String invokeMethod;
    @TableField(value = "input_arguments")
    private String inputArguments;
    @TableField(value = "output_argument")
    private String outputArgument;
    @TableField(value = "is_delete")
    private Byte isDelete;
    @TableField(value = "invoke_type")
    private Byte invokeType;
    @TableField(value = "template_path")
    private String templatePath;
    @TableField(value = "template_content")
    private String templateContent;
    @TableField(value = "template_name")
    private String templateName;
    @TableField(value = "template_file_type")
    private Byte templateFileType;
}
