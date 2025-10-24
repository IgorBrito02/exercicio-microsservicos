package com.exercicio.mspedido.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.enums.StatusPedidoEnum;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @ElementCollection
    @CollectionTable(name = "tb_pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Long> idProdutos = new ArrayList<>();

    public static Pedido fromDto(PedidoDto pedidoDto){
        return new Pedido(pedidoDto.id(), pedidoDto.dataPedido(), pedidoDto.status(), pedidoDto.idProdutos());
    }
}