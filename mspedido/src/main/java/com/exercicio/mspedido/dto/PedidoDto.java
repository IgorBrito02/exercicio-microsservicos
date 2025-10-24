package com.exercicio.mspedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.exercicio.mspedido.enums.StatusPedidoEnum;
import com.exercicio.mspedido.model.Pedido;

public record PedidoDto(
  Long id,
  LocalDateTime dataPedido,
  StatusPedidoEnum status,
  List<Long> idProdutos
) {

  public PedidoDto(Pedido pedido){
    this(
      pedido.getId(),
      pedido.getDataPedido(),
      pedido.getStatus(),
      pedido.getIdProdutos()
    );
  }

}
