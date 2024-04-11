package com.k4sm.prody.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.k4sm.prody.exceptions.ErrorMessage;
import com.k4sm.prody.exceptions.CategoryNotFoundException;
import com.k4sm.prody.exceptions.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(ex.getMessage());
        message.setStatusCode(HttpStatus.BAD_REQUEST.value());
        message.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCategoryNotFoundException(ProductNotFoundException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(ex.getMessage());
        message.setStatusCode(HttpStatus.BAD_REQUEST.value());
        message.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
