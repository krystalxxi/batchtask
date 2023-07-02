package com.ecommerce.batchtask.common.enums;

/**
 * 任务执行状态
 */
public enum TaskProcessStateEnum {
    PROCESSING(1, "进行中"),
    FINISHED(2, "已完成"),
    ERROR(3,"失败");
    Byte code;
    String name;

    TaskProcessStateEnum(Integer code, String name) {
        this.code = code.byteValue();
        this.name = name;
    }

    public Byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
