package com.k4sm.prody.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.k4sm.prody.DTOs.ErrorMessage;
import com.k4sm.prody.Exceptions.ProductNotFoundException;

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
}
