package com.exercicio.msproduto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exercicio.msproduto.dto.ProdutoDto;

public interface ProdutoService {

  Page<ProdutoDto> findAll(Pageable pagination);
  ProdutoDto findById(Long id);
  ProdutoDto save(ProdutoDto produtoDto);
  ProdutoDto update(Long id, ProdutoDto produtoDto);
  void delete(Long id);

}
