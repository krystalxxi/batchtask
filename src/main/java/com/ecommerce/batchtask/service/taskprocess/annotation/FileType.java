package com.ecommerce.batchtask.service.taskprocess.annotation;

import com.ecommerce.batchtask.common.enums.FileTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileType {
    FileTypeEnum type();
}
