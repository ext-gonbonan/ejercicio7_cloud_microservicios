# Ejercicio 5: Microservicio API Gateway

Este proyecto implementa un API Gateway para nuestra arquitectura de microservicios utilizando Spring Cloud Gateway.

## Descripción

El API Gateway actúa como un punto de entrada único para todas las peticiones de los clientes, proporcionando enrutamiento, filtrado y otras funcionalidades centralizadas.

## Características

- Enrutamiento de peticiones a los microservicios apropiados
- Balanceo de carga
- Filtrado de peticiones
- Configuración CORS (Cross-Origin Resource Sharing)

## Configuración

El Gateway está configurado para ejecutarse en el puerto 9000.


## Configuración CORS
Se ha implementado una configuración CORS personalizada para manejar solicitudes de diferentes orígenes. La clase CorsConfig crea un bean CorsWebFilter que permite:

- Solicitudes de cualquier origen
- Todos los métodos HTTP
- Todos los headers en las solicitudes

Esta configuración es necesaria para manejar correctamente las solicitudes CORS a nivel de Gateway, independientemente de la configuración CORS en los microservicios individuales.

## Uso

- Asegúrese de que el servidor Eureka y el servidor de configuración estén en funcionamiento.
- Inicie el Gateway.
- Las peticiones a los microservicios deben realizarse a través del Gateway:
  - Para el servicio de cursos: http://localhost:9000/scursos/...
  - Para el servicio de formación: http://localhost:9000/sformacion/...



## Dependencias

- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka Client


## Notas sobre CORS
La implementación de CORS en el Gateway resuelve problemas comunes con solicitudes de preflight OPTIONS y asegura que las peticiones desde diferentes orígenes (como aplicaciones frontend) sean manejadas correctamente. Esta configuración es más robusta que la configuración YAML y se aplica de manera consistente en todos los escenarios.

![imagen](https://github.com/user-attachments/assets/322a3278-072d-4ace-a4a6-cbbb428531ca)

