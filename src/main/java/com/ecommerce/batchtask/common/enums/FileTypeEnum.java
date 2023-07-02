package com.ecommerce.batchtask.common.enums;

/**
 * 文件类型
 */
public enum FileTypeEnum {
    EXCEL(1, "excel"),
    CSV(2, "csv");
    Byte code;
    String name;

    FileTypeEnum(Integer code, String name) {
        this.code = code.byteValue();
        this.name = name;
    }

    public static FileTypeEnum getFileTypeByCode(Byte code){
        FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
        for(FileTypeEnum fileTypeEnum: fileTypeEnums){
            if (fileTypeEnum.getCode().equals(code)){
                return fileTypeEnum;
            }
        }
        return null;
    }

    public Byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
