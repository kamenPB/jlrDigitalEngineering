package com.jrl.exercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VinNotFoundException extends RuntimeException {
    public VinNotFoundException(String msg){
        super(msg);
    }
}
