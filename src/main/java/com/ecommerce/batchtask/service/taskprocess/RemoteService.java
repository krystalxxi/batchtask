package com.ecommerce.batchtask.service.taskprocess;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * 远程服务调用
 */
@SPI
public interface RemoteService {
    /**
     * @return
     */
    @Adaptive({"type"})
    Object invoke(URL url) throws Exception;
}
