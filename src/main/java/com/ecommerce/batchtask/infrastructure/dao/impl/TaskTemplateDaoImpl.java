package com.ecommerce.batchtask.infrastructure.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.batchtask.infrastructure.dao.TaskTemplateDao;
import com.ecommerce.batchtask.infrastructure.persistence.mapper.TaskTemplateMapper;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskTemplateDaoImpl extends ServiceImpl<TaskTemplateMapper, TaskTemplate> implements TaskTemplateDao {
}
