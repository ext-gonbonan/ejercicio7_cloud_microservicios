package com.vuelo.inicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.vuelo.dao.VueloDao;
import com.vuelo.model.Vuelo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// Vuelo
@EnableDiscoveryClient
@EntityScan(basePackages = { "com.vuelo.model" })
@EnableJpaRepositories(basePackages = { "com.vuelo.dao" })
@OpenAPIDefinition(info = @Info(title = "Vuelo API", version = "1.0", description = "Documentación Vuelo API v1.0"))
@SpringBootApplication(scanBasePackages = { "com.vuelo.controller", "com.vuelo.service", "com.vuelo.inicio" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner initVueloData(VueloDao vueloDao) {
		return args -> {
			// Verificamos si la base de datos está vacía
			if (vueloDao.count() == 0) {
				Random random = new Random();
				LocalDate startDate = LocalDate.now();
				// Creamos y guardamos
				List<Vuelo> vuelos = List.of(
						// Iberia (4 vuelos)
						createVuelo("Iberia", startDate, random, 150.00, 250.00, 100, 200),
						createVuelo("Iberia", startDate, random, 150.00, 250.00, 100, 200),
						createVuelo("Iberia", startDate, random, 150.00, 250.00, 100, 200),
						createVuelo("Iberia", startDate, random, 150.00, 250.00, 100, 200),

						// Binter (4 vuelos)
						createVuelo("Binter", startDate, random, 80.00, 150.00, 50, 100),
						createVuelo("Binter", startDate, random, 80.00, 150.00, 50, 100),
						createVuelo("Binter", startDate, random, 80.00, 150.00, 50, 100),
						createVuelo("Binter", startDate, random, 80.00, 150.00, 50, 100),

						// Canaryfly (3 vuelos)
						createVuelo("Canaryfly", startDate, random, 70.00, 120.00, 40, 80),
						createVuelo("Canaryfly", startDate, random, 70.00, 120.00, 40, 80),
						createVuelo("Canaryfly", startDate, random, 70.00, 120.00, 40, 80),

						// Ryanair (2 vuelos)
						createVuelo("Ryanair", startDate, random, 50.00, 200.00, 150, 200),
						createVuelo("Ryanair", startDate, random, 50.00, 200.00, 150, 200),

						// EasyJet (2 vuelos)
						createVuelo("EasyJet", startDate, random, 60.00, 180.00, 140, 200),
						createVuelo("EasyJet", startDate, random, 60.00, 180.00, 140, 200),

						// Vueling (2 vuelos)
						createVuelo("Vueling", startDate, random, 70.00, 190.00, 130, 200),
						createVuelo("Vueling", startDate, random, 70.00, 190.00, 130, 200),

						// Air Europa (3 vuelos)
						createVuelo("Air Europa", startDate, random, 100.00, 280.00, 120, 200),
						createVuelo("Air Europa", startDate, random, 100.00, 280.00, 120, 200),
						createVuelo("Air Europa", startDate, random, 100.00, 280.00, 120, 200));

				vueloDao.saveAll(vuelos);
				
				System.out.println("Base de datos inicializada con 20 vuelos de diferentes compañías.");
			} else {
				System.out.println("La base de datos ya contiene vuelos. No se inicializaron nuevos datos.");
			}
		};
	}

	private Vuelo createVuelo(String compania, LocalDate startDate, Random random, double minPrecio, double maxPrecio, int minPlazas, int maxPlazas) {
		
		return Vuelo.builder()
				.compania(compania)
				.fechaVuelo(startDate.plusDays(random.nextInt(30)))
				.precio(Math.round((minPrecio + random.nextDouble() * (maxPrecio - minPrecio)) * 100.0) / 100.0)
				.plazasDisponibles(minPlazas + random.nextInt(maxPlazas - minPlazas + 1))
				.build();
	}

}
