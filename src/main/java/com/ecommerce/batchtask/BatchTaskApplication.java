package com.ecommerce.batchtask;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@MapperScan(basePackages = "com.ecommerce.batchtask.infrastructure.persistence.mapper")
public class BatchTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchTaskApplication.class, args);
	}

}
