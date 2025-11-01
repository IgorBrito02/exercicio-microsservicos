package com.exercicio.mspagamento.service;

import com.exercicio.mspagamento.dto.PagamentoDto;
import java.math.BigDecimal;

public interface PagamentoService {
    PagamentoDto registrarPagamento(Long pedidoId, BigDecimal valor);
}