package com.ecommerce.batchtask.controller;

import com.ecommerce.basicplatform.vo.ResultVo;
import com.ecommerce.batchtask.controller.assembler.DataMapper;
import com.ecommerce.batchtask.controller.dto.TaskTemplateDto;
import com.ecommerce.batchtask.service.tasktemplate.TaskTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 批量任务模板管理
 */
@RestController
public class TaskTemplateController {
    @Autowired
    private TaskTemplateService taskTemplateService;

    /**
     * 创建模板
     *
     * @param taskTemplate
     * @return
     */
    @PostMapping("/batch/createTaskTemplate")
    public ResultVo<Boolean> createTaskTemplate(@RequestBody TaskTemplateDto taskTemplate) {
        taskTemplateService.createTaskTemplate(DataMapper.INSTANCE.toTaskTemplateBo(taskTemplate));
        return ResultVo.newSuccessResult(Boolean.TRUE);
    }

    /**
     * 查询所有模板
     * @return
     */
    @RequestMapping("/queryTaskTemplates")
    public ResultVo<List<TaskTemplateDto>> queryTaskTemplates(){
        return ResultVo.newSuccessResult(null);
    }
}
