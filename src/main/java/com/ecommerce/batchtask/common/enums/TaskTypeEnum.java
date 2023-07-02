package com.ecommerce.batchtask.common.enums;

/**
 * 任务类型
 */
public enum TaskTypeEnum {
    IMPORT(1, "IMPORT"),
    EXPORT(2, "EXPORT");
    Byte code;
    String name;

    TaskTypeEnum(Integer code, String name) {
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
