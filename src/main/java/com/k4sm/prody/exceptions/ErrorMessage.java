package com.k4sm.prody.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorMessage {
  String message;
  HttpStatus Status;
  int StatusCode;
}
