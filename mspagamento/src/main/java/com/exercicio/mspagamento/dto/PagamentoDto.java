package com.exercicio.mspagamento.dto;

import com.exercicio.mspagamento.model.Pagamento;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoDto(
    Long id,
    UUID codigo,
    Long pedidoId,
    BigDecimal valor,
    String status,
    LocalDateTime dataCriacao,
    LocalDateTime dataExpiracao
) {
    public PagamentoDto(Pagamento pagamento) {
        this(
            pagamento.getId(),
            pagamento.getCodigo(),
            pagamento.getPedidoId(),
            pagamento.getValor(),
            pagamento.getStatus(),
            pagamento.getDataCriacao(),
            pagamento.getDataExpiracao()
        );
    }
}