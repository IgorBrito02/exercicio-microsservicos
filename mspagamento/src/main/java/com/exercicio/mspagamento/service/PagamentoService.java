package com.exercicio.mspagamento.service;

import com.exercicio.mspagamento.dto.PagamentoDto;

public interface PagamentoService {
    PagamentoDto registrarPagamento(PagamentoDto dto);
    PagamentoDto buscarPorId(Long id);
}