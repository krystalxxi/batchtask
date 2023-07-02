package com.ecommerce.batchtask.common.exceptions;

import lombok.Data;

/**
 * 业务层异常
 */
@Data
public class BatchTaskProcessBizException extends Exception {
    //异常信息
    public String message;

    public String errorCode;

    public BatchTaskProcessBizException(String message) {
        super(message);
        this.message = message;
    }

    public BatchTaskProcessBizException(String errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
}
