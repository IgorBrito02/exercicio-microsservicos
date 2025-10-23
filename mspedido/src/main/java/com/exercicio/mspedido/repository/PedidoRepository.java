package com.exercicio.mspedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.mspedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}