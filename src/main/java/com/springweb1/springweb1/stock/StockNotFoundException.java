package com.springweb1.springweb1.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {


    public StockNotFoundException(String s) {
        super(s);
    }
}
