package com.ecommerce.batchtask.common.handlers;

import com.ecommerce.basicplatform.vo.ResultVo;
import com.ecommerce.batchtask.common.exceptions.BatchTaskProcessBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BatchTaskProcessBizException.class)
    public ResultVo exceptionHandler(BatchTaskProcessBizException e) {
        return ResultVo.newExceptionResult("-100", e.getMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultVo exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("URL : " + req.getRequestURL().toString());
        log.error("HTTP_METHOD : " + req.getMethod());
        log.error("发生空指针异常！原因是:", e);
        return ResultVo.newExceptionResult("-99", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        return ResultVo.newExceptionResult("-110", "操作失败");
    }

}
