package com.vuelo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
	ErrorResponse errorResponse = new ErrorResponse(
            ex.getStatusCode().value(),
            ex.getReason(),
            simplifyMessage(ex.getMessage()),
            request.getRequestURI() // Obtiene la URI de la solicitud actual
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
	
	private String simplifyMessage(String message) {
        // Lógica para simplificar el mensaje de error
        if (message.contains(":")) {
            return message.split(":")[0] + ": " + message.split(":")[1];
        }
        return message;
    }

}