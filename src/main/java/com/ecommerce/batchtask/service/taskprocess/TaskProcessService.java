package com.ecommerce.batchtask.service.taskprocess;


import com.ecommerce.basicplatform.param.PageParam;
import com.ecommerce.batchtask.service.taskprocess.bo.CreateTaskBo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskProcessBo;

import java.util.List;

/**
 * 任务执行服务
 */
public interface TaskProcessService {
    /**
     * 创建任务
     * @param createTaskBo
     * @return
     */
    boolean createTask(CreateTaskBo createTaskBo) throws Exception;

    /**
     * 查询任务列表
     * @param param
     * @return
     */
    List<TaskProcessBo> queryTaskList(PageParam param) ;

}
