package com.exercicio.mspagamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID codigo;
    private Long pedidoId;
    private BigDecimal valor;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;

    public static Pagamento novoPagamento(Long pedidoId, BigDecimal valor) {
        Pagamento pagamento = new Pagamento();
        pagamento.setCodigo(UUID.randomUUID());
        pagamento.setPedidoId(pedidoId);
        pagamento.setValor(valor);
        pagamento.setStatus("CONFIRMADO");
        pagamento.setDataCriacao(LocalDateTime.now());
        pagamento.setDataExpiracao(LocalDateTime.now().plusHours(24));
        return pagamento;
    }
}