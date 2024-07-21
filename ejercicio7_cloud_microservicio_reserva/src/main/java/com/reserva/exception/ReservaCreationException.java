package com.reserva.exception;

import org.springframework.http.HttpStatus;

public class ReservaCreationException extends BaseException {
   
	private static final long serialVersionUID = 1L;

	public ReservaCreationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
    
}