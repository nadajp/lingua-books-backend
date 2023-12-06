package com.lingua.market.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 123L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
