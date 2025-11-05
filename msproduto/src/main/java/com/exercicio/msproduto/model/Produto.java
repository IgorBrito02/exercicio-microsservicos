package com.exercicio.msproduto.model;

import com.exercicio.msproduto.dto.ProdutoDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer quantidade;
    private String descricao;
    private Float preco;

    public static Produto fromDto(ProdutoDto dto) {
        return Produto.builder()
                .id(dto.id())
                .nome(dto.nome())
                .descricao(dto.descricao())
                .quantidade(dto.quantidade())
                .preco(dto.preco())
                .build();
    }
}
