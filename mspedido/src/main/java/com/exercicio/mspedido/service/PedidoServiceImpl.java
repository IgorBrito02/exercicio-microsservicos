package com.exercicio.mspedido.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.enums.StatusPedidoEnum;
import com.exercicio.mspedido.model.Pedido;
import com.exercicio.mspedido.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    @Override
    public Page<PedidoDto> findAll(Pageable pagination) {
        return repository.findAll(pagination).map(PedidoDto::new);
    }

    @Override
    public PedidoDto findById(Long id) {
        return repository.findById(id).map(PedidoDto::new).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {
        var pedido = Pedido.fromDto(pedidoDto);
        return new PedidoDto(repository.save(pedido));
    }

    @Transactional
    @Override
    public PedidoDto update(Long id, PedidoDto pedidoDto) {
        var pedido = repository
            .findById(id)
            .orElseThrow(()->new EntityNotFoundException("Pedido não Encontrado"));
        pedido.setDataPedido(pedidoDto.dataPedido());
        pedido.setIdProdutos(pedidoDto.idProdutos());
        pedido.setStatus(pedidoDto.status());
        return new PedidoDto(repository.save(pedido));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository
            .findById(id)
            .orElseThrow(()->new EntityNotFoundException("Pedido não Encontrado"));
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void atualizaStatus(Long id, StatusPedidoEnum status) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        
        pedido.setStatus(status);
        repository.save(pedido);
    }

    
}