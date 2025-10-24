package com.exercicio.msproduto.dto;

import java.time.LocalDateTime;

public record ResponseErrorDto(
  String message,
  String error,
  String status,
  LocalDateTime data
) {

  public ResponseErrorDto(String message, String status){
    this(message, null, status, LocalDateTime.now());
  }

}
