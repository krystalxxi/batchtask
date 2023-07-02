package com.ecommerce.batchtask.infrastructure.persistence.assembler;

import com.ecommerce.batchtask.infrastructure.persistence.po.TaskProcess;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskTemplate;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskProcessBo;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataMapper {
    DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);

    TaskTemplate toTaskTemplate(TaskTemplateBo taskTemplateBo);

    TaskTemplateBo toTaskTemplateBo(TaskTemplate taskTemplate);

    List<TaskProcessBo> toTaskTemplateBoList(List<TaskProcess> taskProcessList);

}
