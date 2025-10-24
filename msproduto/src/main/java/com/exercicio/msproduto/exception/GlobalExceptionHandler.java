package com.exercicio.msproduto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exercicio.msproduto.dto.ResponseErrorDto;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseErrorDto> handleNotFound(EntityNotFoundException ex){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorDto("Not Found", HttpStatus.NOT_FOUND.name()));
  }

}
