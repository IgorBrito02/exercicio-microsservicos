package com.exercicio.msproduto.service;

import com.exercicio.msproduto.model.Produto;
import com.exercicio.msproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listar() { return repository.findAll(); }
    public Produto salvar(Produto p) { return repository.save(p); }
    public Produto atualizar(Long id, Produto p) {
        Produto existente = repository.findById(id).orElseThrow();
        existente.setNome(p.getNome());
        existente.setPreco(p.getPreco());
        return repository.save(existente);
    }
    public void deletar(Long id) { repository.deleteById(id); }
}