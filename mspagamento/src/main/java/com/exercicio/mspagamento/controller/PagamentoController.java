package com.exercicio.mspagamento.controller;

import com.exercicio.mspagamento.dto.PagamentoDto;
import com.exercicio.mspagamento.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    @PostMapping
    public ResponseEntity<PagamentoDto> registrarPagamento(
        @Valid @RequestBody PagamentoDto dto
    ) {
        PagamentoDto saved = service.registrarPagamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> buscarPorId(@PathVariable Long id) {
        PagamentoDto dto = service.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }
}