package com.curso.inicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedMethod("*");
		corsConfig.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}

/*
 * 
 * 1. El error original: 
 *    - Estabamos recibiendo un error 403 FORBIDDEN en las solicitudes OPTIONS, lo que indica
 *      que el Gateway estaba rechazando estas solicitudes de preflight CORS. 
 *    
 * 2. Por qué la configuración en el Gateway lo solucionó: 
 *    a. Manejo explícito de CORS:
 *    - La clase CorsConfig que añadimos crea un bean CorsWebFilter que intercepta todas las
 *      solicitudes entrantes al Gateway. Este filtro se aplica antes de que las solicitudes
 *      lleguen a tus rutas configuradas.
 * 
 * 	  b. Permisividad total:
 *    - corsConfig.addAllowedOrigin("*"): Permite solicitudes de cualquier origen.
 *    - corsConfig.addAllowedMethod("*"): Permite todos los métodos HTTP, incluyendo OPTIONS. 
 *    - corsConfig.addAllowedHeader("*"): Permite todos los headers en las solicitudes.
 * 
 *    c. Aplicación global:
 *    - source.registerCorsConfiguration("/**", corsConfig): Aplica esta configuración a todas 
 *      las rutas del Gateway.
 *  
 * 3. Por qué esto era necesario:
 *    - El Gateway Spring Cloud, por defecto, no tiene una configuración CORS permisiva. 
 *    - Las configuraciones CORS en los microservicios individuales(@CrossOrigin) no afectan 
 *      cómo el Gateway maneja las solicitudes CORS iniciales.
 *    - El navegador envía la solicitud OPTIONS al Gateway, no directamente a tus microservicios.
 * 
 * 4. Cómo resuelve el problema:
 *    - Cuando llega una solicitud OPTIONS, este filtro la intercepta.
 *    - Responde con los headers CORS apropiados, permitiendo la solicitud.
 *    - Esto satisface al navegador, permitiendo que proceda con la solicitud real (GET, POST, etc.).
 *  
 * 5. Por qué la configuración YAML no era suficiente:
 *    - Aunque la configuración YAML puede manejar CORS, la implementación CorsConfig que hemos
 *      usado es más robusta y se aplica de manera más consistente en todos los escenarios.
 * 
 * 
 */