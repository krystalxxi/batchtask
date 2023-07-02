package com.ecommerce.batchtask.service.taskprocess;

import com.ecommerce.batchtask.common.enums.FileTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.annotation.FileType;
import com.ecommerce.batchtask.service.taskprocess.bo.FileHead;
import com.ecommerce.batchtask.service.taskprocess.bo.ImportFile;
import com.ecommerce.batchtask.service.taskprocess.bo.ImportVo;
import com.ecommerce.batchtask.service.taskprocess.bo.TaskBo;
import com.ecommerce.batchtask.service.taskprocess.util.DynamicBeanUtil;
import com.ecommerce.batchtask.service.taskprocess.util.TaskProcessUtil;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导入任务服务
 */
@Component
public class ImportTaskProcessService {
    @Resource
    private List<ImportFileService> importFileServiceList;
    private Map<FileTypeEnum, ImportFileService> importFileServiceMap = Maps.newHashMap();

    private Map<String,Class> proxyClassMap = Maps.newHashMap();

    @PostConstruct
    public void init() {
        if (CollectionUtils.isEmpty(importFileServiceList)) {
            return;
        }
        importFileServiceList.forEach(importFileService -> {
            FileType fileType = AopUtils.getTargetClass(importFileService).getAnnotation(FileType.class);
            importFileServiceMap.put(fileType.type(), importFileService);
        });
    }

    /**
     * 导入
     *
     * @param importTaskBo
     * @return
     */
    public void importAsync(TaskBo importTaskBo) throws Exception{
        ImportFileService importFileService = importFileServiceMap.get(importTaskBo.getFileType());
        if (null == importFileService) {
            return;
        }
        // 解析表头
        List<FileHead> headVoList = TaskProcessUtil.parseTemplateContent(importTaskBo.getTaskTemplateBo().getTemplateContent());

        Map<String,FileHead> properties = new HashMap();
        headVoList.forEach(fileHead -> {
            try {
                properties.put(fileHead.getKey(),fileHead);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Class target = null;
        // 先读取缓存
        if (proxyClassMap.containsKey(importTaskBo.getTaskTemplateBo().getTaskName())){
            target = proxyClassMap.get(importTaskBo.getTaskTemplateBo().getTaskName());
        } else {
            String targetClassName = ImportVo.class.getPackage().getName() + "." + importTaskBo.getTaskTemplateBo().getTaskName();
            DynamicBeanUtil.generateObject(targetClassName,properties);
            proxyClassMap.put(importTaskBo.getTaskTemplateBo().getTaskName(),Class.forName(targetClassName));
        }
        ImportFile importFile = ImportFile.builder()
                .importFilePath(importTaskBo.getImportFilePath())
                .target(target)
                .build();
        importFileService.doImport(importFile);
    }


}
