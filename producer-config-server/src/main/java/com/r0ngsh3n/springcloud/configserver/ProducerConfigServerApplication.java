package com.r0ngsh3n.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ProducerConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerConfigServerApplication.class, args);
	}

}
