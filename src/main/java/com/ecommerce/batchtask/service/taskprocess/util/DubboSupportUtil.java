package com.ecommerce.batchtask.service.taskprocess.util;

import com.alibaba.fastjson.JSONObject;
import com.ecommerce.batchtask.service.taskprocess.RemoteService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

public final class DubboSupportUtil {
    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static JSONObject invoke(URL url) throws Exception {
//        JSONArray ret = null;
        RemoteService remoteService = ExtensionLoader.getExtensionLoader(RemoteService.class).getAdaptiveExtension();
        // todo 异步处理result数据
        Object result = remoteService.invoke(url);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(result));
//        if (null != jsonObject && jsonObject.containsKey("data")) {
//            Object pageObj = jsonObject.get("data");
//            ret = JSONObject.parseArray(JSONObject.toJSONString(pageObj));
//        }
        return jsonObject;
    }

}
