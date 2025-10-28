package com.exercicio.mspedido.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.enums.StatusPedidoEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status = StatusPedidoEnum.CRIADO;

    @ElementCollection
    @CollectionTable(name = "tb_pedido_produtos", joinColumns = @JoinColumn(name = "pedido_id"))
    @Column(name = "produto_id")
    private List<Long> idProdutos = new ArrayList<>();

    public static Pedido fromDto(PedidoDto pedidoDto){
        Pedido pedido = new Pedido();
        pedido.setIdProdutos(pedidoDto.idProdutos());

        return pedido;
    }
}