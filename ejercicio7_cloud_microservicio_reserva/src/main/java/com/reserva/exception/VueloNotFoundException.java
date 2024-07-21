package com.reserva.exception;

import org.springframework.http.HttpStatus;

public class VueloNotFoundException extends BaseException {
   
	private static final long serialVersionUID = 1L;

	public VueloNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    
}