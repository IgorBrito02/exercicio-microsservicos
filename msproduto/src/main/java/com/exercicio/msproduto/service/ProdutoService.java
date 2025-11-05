package com.exercicio.msproduto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exercicio.msproduto.dto.ProdutoDto;
import java.util.List; 

public interface ProdutoService {

  Page<ProdutoDto> findAll(Pageable pagination);
  ProdutoDto findById(Long id);
  ProdutoDto save(ProdutoDto produtoDto);
  ProdutoDto update(Long id, ProdutoDto produtoDto);
  void delete(Long id);

  /**
   * Realiza a baixa no estoque dos produtos.
   * Usado para ser chamado de forma síncrona pelo mspedido após o pagamento ser confirmado.
   * @param produtoIds Lista de IDs de produtos para dar baixa (decremento de 1).
   */
  void realizaBaixaEstoque(List<Long> produtoIds); 
}