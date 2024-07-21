package com.vuelo.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// Vuelo
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.vuelo.model"})
@EnableJpaRepositories(basePackages = {"com.vuelo.dao"})
@OpenAPIDefinition(info = @Info(title = "Vuelo API", version = "1.0", description = "Documentación Vuelo API v1.0"))
@SpringBootApplication(scanBasePackages = {"com.vuelo.controller","com.vuelo.service", "com.vuelo.inicio"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
