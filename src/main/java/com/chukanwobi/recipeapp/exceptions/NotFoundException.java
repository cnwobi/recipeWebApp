package com.chukanwobi.recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super();
    }
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }

    public NotFoundException(String errorMessage, Throwable cause){
        super(errorMessage,cause);
    }
}
