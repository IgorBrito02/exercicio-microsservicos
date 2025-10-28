package com.exercicio.mspedido.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.enums.StatusPedidoEnum;

public interface PedidoService {

  Page<PedidoDto> findAll(Pageable pagination);
  PedidoDto findById(Long id);
  PedidoDto save(PedidoDto pedidoDto);
  PedidoDto update(Long id, PedidoDto pedidoDto);
  void delete(Long id);
  void atualizaStatus(Long id, StatusPedidoEnum status);
  
}
