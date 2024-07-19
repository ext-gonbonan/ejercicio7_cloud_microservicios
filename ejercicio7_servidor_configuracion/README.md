# Ejercicio 5: Microservicio de Configuración

Este proyecto implementa un servidor de configuración centralizado para nuestra arquitectura de microservicios.

## Descripción

El servidor de configuración proporciona una ubicación centralizada para gestionar las propiedades externas de todos los microservicios en diferentes entornos.

## Características

- Configuración centralizada para todos los microservicios
- Soporte para diferentes perfiles (desarrollo, prueba, producción)
- Actualización dinámica de configuraciones

## Configuración

El servidor de configuración está configurado para ejecutarse en el puerto 8888.


## Uso

- Inicie el servidor de configuración después del servidor Eureka y antes que otros microservicios.
- Los microservicios se conectarán automáticamente a este servidor para obtener su configuración.

## Dependencias

- Spring Boot
- Spring Cloud Config Server
