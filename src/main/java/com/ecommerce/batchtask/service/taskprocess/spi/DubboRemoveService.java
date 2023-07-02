package com.ecommerce.batchtask.service.taskprocess.spi;

import com.alibaba.fastjson.JSONObject;

import com.ecommerce.batchtask.common.exceptions.BatchTaskProcessBizException;
import com.ecommerce.batchtask.service.taskprocess.RemoteService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

public class DubboRemoveService implements RemoteService {
    @Override
    public Object invoke(URL url) throws Exception {
        String invokeService = url.getParameter("invokeService");
        String version = url.getParameter("version");
        String invokeMethod = url.getParameter("invokeMethod");
        String inputArguments = url.getParameter("inputArguments");
        String param = url.getParameter("param");

        if (StringUtils.isEmpty(invokeService) || StringUtils.isEmpty(invokeMethod)) {
            throw new BatchTaskProcessBizException("模板格式不正确，缺少必填参数");
        }
        Object arg0 = null;
        if (StringUtils.isNotBlank(param)) {
            JSONObject jsonObject = JSONObject.parseObject(param);
            if (null != jsonObject){
                Class c = Class.forName(inputArguments);
                arg0 = jsonObject.toJavaObject(c);
            }
        }
        Object result;
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setInterface(invokeService);
        reference.setGeneric(true);
        GenericService genericService = reference.get();
        try {
            result = genericService.$invoke(invokeMethod, new String[]{inputArguments}, new Object[]{arg0});
        } catch (Exception e) {
            throw new BatchTaskProcessBizException("调用远程服务失败,{}", e.getMessage());
        }
        return result;
    }
}
