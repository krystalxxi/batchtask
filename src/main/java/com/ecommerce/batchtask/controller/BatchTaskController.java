package com.ecommerce.batchtask.controller;

import com.ecommerce.basicplatform.param.PageParam;
import com.ecommerce.basicplatform.vo.PageResult;
import com.ecommerce.basicplatform.vo.ResultVo;
import com.ecommerce.batchtask.controller.assembler.DataMapper;
import com.ecommerce.batchtask.controller.dto.CreateTaskDto;
import com.ecommerce.batchtask.controller.dto.TaskProcessDto;
import com.ecommerce.batchtask.service.taskprocess.TaskProcessService;
import com.ecommerce.batchtask.service.taskprocess.bo.CreateTaskBo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskProcessBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BatchTaskController {
    @Autowired
    private TaskProcessService taskProcessService;

    /**
     * 创建任务
     *
     * @return
     */
    @PostMapping("/batch/createTask")
    public ResultVo<Integer> createTask(@RequestBody CreateTaskDto createTask) throws Exception {
        CreateTaskBo createTaskBo = DataMapper.INSTANCE.toCreateTaskBo(createTask);
        taskProcessService.createTask(createTaskBo);
        return ResultVo.newSuccessResult(Boolean.TRUE);
    }

    /**
     * 查询执行任务列表
     *
     * @return
     */
    @PostMapping("/batch/queryTask")
    public ResultVo<PageResult<TaskProcessDto>> queryProcessTaskList(@RequestBody PageParam<TaskProcessDto> param) {
        List<TaskProcessBo> taskProcessBoList = taskProcessService.queryTaskList(param);
        PageResult pageResult = new PageResult();
        pageResult.setData(DataMapper.INSTANCE.toTaskProcessDtoList(taskProcessBoList));
        return ResultVo.newSuccessResult(pageResult);
    }
}
