package com.sh.carexx.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryBootstrap {
	public static void main(String[] args) {
		SpringApplication.run(RegistryBootstrap.class, args);
	}
}
