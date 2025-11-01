package com.exercicio.mspagamento.controller;

import com.exercicio.mspagamento.dto.PagamentoDto;
import com.exercicio.mspagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @PostMapping
    public ResponseEntity<PagamentoDto> registrarPagamento(
            @RequestParam Long pedidoId,
            @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(service.registrarPagamento(pedidoId, valor));
    }
}