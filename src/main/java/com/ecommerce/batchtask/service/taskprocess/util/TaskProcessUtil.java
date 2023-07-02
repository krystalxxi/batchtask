package com.ecommerce.batchtask.service.taskprocess.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.bo.FileHead;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务处理工具类
 */
@Slf4j
public final class TaskProcessUtil {
    /**
     * 获取文件类型
     * @param fileName
     * @return
     */
    public static FileTypeEnum getFileType(String fileName) {
        if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return FileTypeEnum.EXCEL;
        } else if (fileName.endsWith(".csv")) {
            return FileTypeEnum.CSV;
        }
        return null;
    }

    /**
     * 解析模板内容
     * @param content
     * @return
     */
    public static List<FileHead> parseTemplateContent(String content) {
        List<FileHead> headVos = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(content);
        if (jsonObject.containsKey("cols")) {
            JSONArray jsonArray = jsonObject.getJSONArray("cols");
            FileHead headVo;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                headVo = new FileHead();
                headVo.setKey(object.getString("key"));
                headVo.setIndex(object.getInteger("index"));
                if(object.containsKey("fieldType")){
                    headVo.setField(object.getString("fieldType"));
                }
                headVos.add(headVo);
            }
        }
        return headVos;
    }

}
