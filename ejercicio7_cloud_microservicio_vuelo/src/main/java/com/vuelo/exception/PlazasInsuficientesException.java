package com.vuelo.exception;

import org.springframework.http.HttpStatus;

public class PlazasInsuficientesException extends BaseException {
    
	private static final long serialVersionUID = 1L;

	public PlazasInsuficientesException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
    
}