package com.exercicio.msproduto.service;

import com.exercicio.msproduto.dto.ProdutoDto;
import com.exercicio.msproduto.model.Produto;
import com.exercicio.msproduto.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    public Page<ProdutoDto> findAll(Pageable pagination) {
        return repository.findAll(pagination).map(ProdutoDto::new);
    }

    @Override
    public ProdutoDto findById(Long id) {
        return repository.findById(id).map(ProdutoDto::new).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public ProdutoDto save(ProdutoDto produtoDto) {
        var produto = Produto.fromDto(produtoDto);
        return new ProdutoDto(repository.save(produto));
    }

    @Transactional
    @Override
    public ProdutoDto update(Long id, ProdutoDto produtoDto) {
        var produto = repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não Encontrado"));
        
        produto.setNome(produtoDto.nome());
        produto.setQuantidade(produtoDto.quantidade());
        produto.setDescricao(produtoDto.descricao());
        produto.setPreco(produtoDto.preco());

        return new ProdutoDto(repository.save(produto));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não Encontrado"));
        repository.deleteById(id);
    }
    
}