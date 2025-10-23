package com.exercicio.mspedido.controller;

import com.exercicio.mspedido.model.Pedido;
import com.exercicio.mspedido.model.StatusPedido;
import com.exercicio.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return service.criarPedido(pedido);
    }

    @PutMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return service.atualizarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        service.deletarPedido(id);
    }
}
