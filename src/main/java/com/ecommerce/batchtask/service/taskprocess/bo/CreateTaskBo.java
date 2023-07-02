package com.ecommerce.batchtask.service.taskprocess.bo;

import com.alibaba.fastjson.JSONObject;
import com.ecommerce.basicplatform.param.PageParam;
import lombok.Data;

@Data
public class CreateTaskBo extends PageParam {
    private String taskName;
    private Byte taskType;
    private String data;

    private long id;

    /**
     * 导入文件地址
     */
    private String importFilePath;

//    public void setData(String data) {
//        JSONObject jsonObject = JSONObject.parseObject(data);
//        if (jsonObject.containsKey("pageNo")) {
//            jsonObject.put("pageNo", this.getPageNo());
//            jsonObject.put("pageSize", this.getPageSize());
//        }
//        this.data = jsonObject.toJSONString();
//    }
}
