package com.natan.clientmanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ClientManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientManagementApiApplication.class, args);
	}

}
