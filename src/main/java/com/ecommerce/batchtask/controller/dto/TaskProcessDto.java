package com.ecommerce.batchtask.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskProcessDto implements Serializable {
    private Integer id;
    private Integer taskId;
    private Byte state;
    private String exportUrl;
    private Byte taskType;
}
