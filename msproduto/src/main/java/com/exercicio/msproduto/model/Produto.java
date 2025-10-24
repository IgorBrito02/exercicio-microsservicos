package com.exercicio.msproduto.model;

import com.exercicio.msproduto.dto.ProdutoDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nome;

    private Integer quantidade;

    @Lob
    private String descricao;

    private Float preco;

    public static Produto fromDto(ProdutoDto produtoDto){
        return new Produto(produtoDto.id(), produtoDto.nome(), produtoDto.quantidade(), produtoDto.descricao(), produtoDto.preco());
    }
}
