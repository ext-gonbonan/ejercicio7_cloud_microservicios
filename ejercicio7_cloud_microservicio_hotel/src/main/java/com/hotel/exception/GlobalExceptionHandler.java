package com.hotel.exception;

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
            ex.getLocalizedMessage(),
            request.getRequestURI() // Obtiene la URI de la solicitud actual
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}