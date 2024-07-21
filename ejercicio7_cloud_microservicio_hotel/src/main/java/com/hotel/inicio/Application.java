package com.hotel.inicio;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.hotel.dao.HotelDao;
import com.hotel.model.Hotel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

// Hotel
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.hotel.model"})
@EnableJpaRepositories(basePackages = {"com.hotel.dao"})
@OpenAPIDefinition(info = @Info(title = "Hotel API", version = "1.0", description = "Documentación Hotel API v1.0"))
@SpringBootApplication(scanBasePackages = {"com.hotel.controller","com.hotel.service", "com.hotel.inicio"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner initData(HotelDao hotelDao) {
        return args -> {
            // Verificamos si la base de datos está vacía
            if (hotelDao.count() == 0) {
                // Creamos y guardamos 20 hoteles en las Islas Canarias
                hotelDao.saveAll(List.of(
                    Hotel.builder().nombre("Hotel Santa Catalina").categoria("5 estrellas").precio(250.00).disponible(true).build(),
                    Hotel.builder().nombre("Seaside Grand Hotel Residencia").categoria("5 estrellas").precio(300.00).disponible(true).build(),
                    Hotel.builder().nombre("Adrián Hoteles Roca Nivaria").categoria("5 estrellas").precio(280.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Riu Palace Tenerife").categoria("4 estrellas").precio(200.00).disponible(true).build(),
                    Hotel.builder().nombre("Lopesan Baobab Resort").categoria("5 estrellas").precio(270.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Jardines de Nivaria").categoria("5 estrellas").precio(320.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Dreams Playa Blanca").categoria("4 estrellas").precio(180.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Suite Villa María").categoria("5 estrellas").precio(290.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Las Madrigueras Golf Resort & Spa").categoria("5 estrellas").precio(310.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Taburiente").categoria("4 estrellas").precio(150.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Cordial Mogán Playa").categoria("4 estrellas").precio(220.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Escuela Santa Brígida").categoria("4 estrellas").precio(140.00).disponible(true).build(),
                    Hotel.builder().nombre("H10 Costa Adeje Palace").categoria("4 estrellas").precio(200.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Parador de La Gomera").categoria("3 estrellas").precio(160.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Parador de El Hierro").categoria("3 estrellas").precio(150.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Parador de Las Cañadas del Teide").categoria("3 estrellas").precio(170.00).disponible(true).build(),
                    Hotel.builder().nombre("Iberostar Grand El Mirador").categoria("5 estrellas").precio(330.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Gran Meliá Palacio de Isora").categoria("5 estrellas").precio(340.00).disponible(true).build(),
                    Hotel.builder().nombre("Hotel Botanico y Oriental Spa Garden").categoria("5 estrellas").precio(350.00).disponible(true).build(),
                    Hotel.builder().nombre("Gran Hotel Atlantis Bahía Real").categoria("5 estrellas").precio(360.00).disponible(true).build()
                ));

                System.out.println("Base de datos inicializada con 20 hoteles en las Islas Canarias.");
            } else {
                System.out.println("La base de datos ya contiene hoteles. No se inicializaron nuevos datos.");
            }
        };
    }
    
}