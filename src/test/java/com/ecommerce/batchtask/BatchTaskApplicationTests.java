package com.ecommerce.batchtask;

import com.ecommerce.batchtask.common.enums.TaskTypeEnum;
import com.ecommerce.batchtask.service.taskprocess.TaskProcessService;
import com.ecommerce.batchtask.service.taskprocess.bo.CreateTaskBo;
import com.ecommerce.batchtask.service.tasktemplate.TaskTemplateService;
import com.ecommerce.batchtask.service.tasktemplate.bo.TaskTemplateBo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchTaskApplicationTests {
	@Autowired
	private TaskTemplateService taskTemplateService;

	@Autowired
	private TaskProcessService taskProcessService;



	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateTemplate(){
		TaskTemplateBo templateBo = new TaskTemplateBo();
		templateBo.setTemplateName("SKU_INFO_EXPORT.xlsx");
		templateBo.setInvokeService("com.ecommerce.itemcenter.api.SkuAppService");
		templateBo.setInvokeMethod("querySkuInfo");
		templateBo.setTaskType(TaskTypeEnum.EXPORT.getCode());
		templateBo.setTaskName("EXPORT_SKU_INFO");
		templateBo.setInputArguments("[{\"fieldName\":\"param\",\"fieldType\":\"com.ecommerce.basicplatform.param.PageParam\"}]");
		templateBo.setTemplateContent("[{\"key\":\"skuId\",\"index\":0},{\"key\":\"skuName\",\"index\":1},{\"key\":\"salePrice\",\"index\":3},{\"key\":\"brandId\",\"index\":4},{\"key\":\"categoryId\",\"index\":5}]");
		taskTemplateService.createTaskTemplate(templateBo);
	}

	@Test
	public void createTask() throws Exception{
		CreateTaskBo createTaskBo = new CreateTaskBo();
		createTaskBo.setTaskName("EXPORT_SKU_INFO");
		createTaskBo.setTaskType(TaskTypeEnum.EXPORT.getCode());
		createTaskBo.setPageNo(1);
		createTaskBo.setPageSize(10);
		createTaskBo.setData("{\"pageSize\":10,\"pageNo\":1}");
		taskProcessService.createTask(createTaskBo);
//		Thread.currentThread().join();
	}

	@Test
	public void testImport() throws Exception{
		CreateTaskBo createTaskBo = new CreateTaskBo();
		createTaskBo.setTaskName("IMPORT_SKU_INFO");
		createTaskBo.setTaskType(TaskTypeEnum.IMPORT.getCode());
		createTaskBo.setImportFilePath("D:\\ecommerce\\download\\SKU_INFO_EXPORT.xlsx");
		taskProcessService.createTask(createTaskBo);
	}
}
