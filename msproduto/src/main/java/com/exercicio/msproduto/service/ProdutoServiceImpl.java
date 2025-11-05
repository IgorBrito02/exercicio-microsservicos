package com.exercicio.msproduto.service;

import com.exercicio.msproduto.dto.ProdutoDto;
import com.exercicio.msproduto.model.Produto;
import com.exercicio.msproduto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final Logger LOGGER = Logger.getLogger(ProdutoServiceImpl.class.getName());
    private final ProdutoRepository repository;

    @Override
    public Page<ProdutoDto> findAll(Pageable pagination) {
        return repository.findAll(pagination).map(ProdutoDto::new);
    }

    @Override
    public ProdutoDto findById(Long id) {
        return repository.findById(id)
                .map(ProdutoDto::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ProdutoDto save(ProdutoDto produtoDto) {
        var produto = Produto.fromDto(produtoDto);
        return new ProdutoDto(repository.save(produto));
    }

    @Override
    public ProdutoDto update(Long id, ProdutoDto produtoDto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        // Atualiza os campos desejados
        produto.setNome(produtoDto.nome());
        produto.setPreco(produtoDto.preco());
        produto.setQuantidade(produtoDto.quantidade());
        return new ProdutoDto(repository.save(produto));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void realizaBaixaEstoque(List<Long> produtoIds) {
        for (Long produtoId : produtoIds) {
            Produto produto = repository.findById(produtoId)
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + produtoId));

            if (produto.getQuantidade() > 0) {
                produto.setQuantidade(produto.getQuantidade() - 1);
                repository.save(produto);
            } else {
                LOGGER.log(Level.WARNING, "Atenção: Estoque zerado para o produto {0}.", produtoId);
            }
        }
    }
}