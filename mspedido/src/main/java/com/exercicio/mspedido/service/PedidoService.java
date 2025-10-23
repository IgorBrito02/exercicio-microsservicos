package com.exercicio.mspedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.mspedido.model.Pedido;
import com.exercicio.mspedido.model.StatusPedido;
import com.exercicio.mspedido.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        return repository.save(pedido);
    }

    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
        pedido.setStatus(novoStatus);
        return repository.save(pedido);
    }

    public void deletarPedido(Long id) {
        repository.deleteById(id);
    }
}