package com.curso.inicio;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicios de Gestión de Viajes")
                        .version("1.0")
                        .description("Esta API proporciona acceso a los servicios de Hotel, Vuelo y Reserva. " +
                                "Seleccione un servicio específico del menú desplegable superior para ver sus operaciones.")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Antonio González Bonilla")
                                .email("antonio.gonzalez@altia.es")
                                .url("http://www.tu-sitio-web.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
    
}