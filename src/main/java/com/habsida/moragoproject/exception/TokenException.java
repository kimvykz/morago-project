package com.habsida.moragoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TokenException(String token, String message) {
        super(String.format("Failed for [%s]: $s", token, message));
    }
}
