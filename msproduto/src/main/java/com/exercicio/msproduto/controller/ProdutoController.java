package com.exercicio.msproduto.controller;

import com.exercicio.msproduto.model.Produto;
import com.exercicio.msproduto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listar() { return service.listar(); }

    @PostMapping
    public Produto salvar(@RequestBody Produto p) { return service.salvar(p); }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto p) { return service.atualizar(id, p); }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }
}