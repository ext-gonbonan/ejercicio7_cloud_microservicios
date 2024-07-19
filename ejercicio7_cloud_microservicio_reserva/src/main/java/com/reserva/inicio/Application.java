package com.reserva.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Reserva
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.reserva.model"})
@EnableJpaRepositories(basePackages = {"com.reserva.dao"})
@SpringBootApplication(scanBasePackages = {"com.reserva.controller","com.reserva.service", "com.reserva.inicio"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
