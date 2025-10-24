package com.exercicio.mspedido.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ResponseErrorDto {
    private final String message;
    private String error;
    private final String status;
    private LocalDateTime data = LocalDateTime.now();
}
