package com.exercicio.mspedido.model;

import java.time.LocalDateTime;
import java.util.List;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private List<Long> idProdutos;

    public static Pedido fromDto(PedidoDto dto) {
        return Pedido.builder()
                .id(dto.id())
                .dataPedido(dto.dataPedido())
                .status(dto.status())
                .idProdutos(dto.idProdutos())
                .build();
    }
}