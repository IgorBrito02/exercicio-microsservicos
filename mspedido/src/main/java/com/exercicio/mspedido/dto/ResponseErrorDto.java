package com.exercicio.mspedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDto {
    private String message;
    private String error;
    private int status;
    private Object data;
}