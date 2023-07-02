package com.ecommerce.batchtask.service.tasktemplate;

import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;

/**
 * 模板管理服务
 */
public interface TaskTemplateService {

    /**
     * 创建模板
     * @param taskTemplateBo
     */
    void createTaskTemplate(TaskTemplateBo taskTemplateBo);

    /**
     * 获取模板
     * @param taskName
     * @param taskType
     * @return
     * @throws Exception
     */
    TaskTemplateBo getTaskTemplate(String taskName, Byte taskType);
}
