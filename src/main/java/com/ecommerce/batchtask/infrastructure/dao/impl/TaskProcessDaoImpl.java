package com.ecommerce.batchtask.infrastructure.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecommerce.batchtask.infrastructure.dao.TaskProcessDao;
import com.ecommerce.batchtask.infrastructure.persistence.mapper.TaskProcessMapper;
import com.ecommerce.batchtask.infrastructure.persistence.po.TaskProcess;
import org.springframework.stereotype.Repository;

@Repository
public class TaskProcessDaoImpl extends ServiceImpl<TaskProcessMapper, TaskProcess> implements TaskProcessDao {
}
