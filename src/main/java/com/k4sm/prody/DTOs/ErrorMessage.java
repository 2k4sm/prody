package com.k4sm.prody.DTOs;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    String message;
    HttpStatus Status;
    int StatusCode;
}
