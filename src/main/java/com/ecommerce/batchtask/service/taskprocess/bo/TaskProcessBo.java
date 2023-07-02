package com.ecommerce.batchtask.service.taskprocess.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskProcessBo implements Serializable {
    private Integer id;
    private Integer taskId;
    private Byte state;
    private String exportUrl;
    private Byte taskType;
}
