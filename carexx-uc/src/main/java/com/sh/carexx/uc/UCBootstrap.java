package com.sh.carexx.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = { "com.sh.carexx.*" })
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan(basePackages = "com.sh.carexx.uc.dao")
public class UCBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(UCBootstrap.class, args);
	}
	
}
