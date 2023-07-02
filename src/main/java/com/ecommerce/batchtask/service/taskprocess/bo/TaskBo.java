package com.ecommerce.batchtask.service.taskprocess.bo;

import com.ecommerce.basicplatform.param.PageParam;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskBo extends PageParam {

    /**
     * task id
     */
    private Integer taskId;

    /**
     * 文件类型
     */
    private FileTypeEnum fileType;

    /**
     * 页面传参
     */
    private String data;

    /**
     * 模板
     */
    private TaskTemplateBo taskTemplateBo;

    /**
     * 导入文件路径
     */
    private String importFilePath;
}
