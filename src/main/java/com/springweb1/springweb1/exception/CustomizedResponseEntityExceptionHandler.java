package com.springweb1.springweb1.exception;

import com.springweb1.springweb1.product.ProductNotFoundException;
import com.springweb1.springweb1.stock.StockNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Controller
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
     ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date(), "CUSTOM message", request.getDescription(false));

    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ProductNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(StockNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(StockNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse =   new ExceptionResponse(new Date(), "validation error", ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);    }
}
