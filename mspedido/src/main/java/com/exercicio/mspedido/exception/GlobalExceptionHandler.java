package com.exercicio.mspedido.exception;

import com.exercicio.mspedido.dto.ResponseErrorDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handleNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorDto("Not Found", HttpStatus.NOT_FOUND.name()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseErrorDto("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.name()));
    }
}
