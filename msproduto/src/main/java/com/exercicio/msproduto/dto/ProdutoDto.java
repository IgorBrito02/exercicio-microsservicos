package com.exercicio.msproduto.dto;

import com.exercicio.msproduto.model.Produto;

public record ProdutoDto(
  Long id,
  String nome,
  Integer quantidade,
  String descricao,
  Float preco
) {

  public ProdutoDto(Produto produto){
    this(
      produto.getId(),
      produto.getNome(),
      produto.getQuantidade(),
      produto.getDescricao(),
      produto.getPreco()
    );
  }

}
