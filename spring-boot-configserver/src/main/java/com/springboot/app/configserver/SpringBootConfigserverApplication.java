package com.springboot.app.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringBootConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigserverApplication.class, args);
	}

}
