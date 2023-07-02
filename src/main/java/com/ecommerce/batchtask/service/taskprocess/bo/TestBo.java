package com.ecommerce.batchtask.service.taskprocess.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
//
//@Setter
//@Getter
//@EqualsAndHashCode
public class TestBo implements Serializable {
    @ExcelProperty(index = 0)
    private Integer skuId;
    @ExcelProperty(index = 1)
    private String skuName;
    @ExcelProperty(index = 2)
    private Integer salePrice;
    @ExcelProperty(index = 3)
    private Integer brandId;
    @ExcelProperty(index = 4)
    private Integer categoryId;
}
