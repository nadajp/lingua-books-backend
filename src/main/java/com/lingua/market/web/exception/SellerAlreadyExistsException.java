package com.lingua.market.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SellerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	
	public SellerAlreadyExistsException(String message) {
		super(message);
	}
}