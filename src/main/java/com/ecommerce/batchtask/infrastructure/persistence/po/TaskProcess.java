package com.ecommerce.batchtask.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "task_process")
public class TaskProcess {
    @TableId(type = IdType.AUTO)
    private int id;
    @TableField(value = "task_id")
    private int taskId;
    private Byte state;
    @TableField(value = "export_url")
    private String exportUrl;
    @TableField(value = "task_type")
    private Byte taskType;
}
