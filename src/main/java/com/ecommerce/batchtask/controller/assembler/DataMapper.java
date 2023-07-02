package com.ecommerce.batchtask.controller.assembler;

import com.ecommerce.batchtask.controller.dto.CreateTaskDto;
import com.ecommerce.batchtask.controller.dto.TaskProcessDto;
import com.ecommerce.batchtask.controller.dto.TaskTemplateDto;
import com.ecommerce.batchtask.service.taskprocess.bo.CreateTaskBo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskProcessBo;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DataMapper {
    DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);

    TaskTemplateBo toTaskTemplateBo(TaskTemplateDto taskTemplateDto);

    CreateTaskBo toCreateTaskBo(CreateTaskDto createTaskDto);

    List<TaskProcessDto> toTaskProcessDtoList(List<TaskProcessBo> taskProcessBoList);

}
