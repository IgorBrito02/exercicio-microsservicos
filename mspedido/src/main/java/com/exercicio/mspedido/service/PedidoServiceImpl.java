package com.exercicio.mspedido.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import com.exercicio.mspedido.dto.PedidoDto;
import com.exercicio.mspedido.enums.StatusPedidoEnum;
import com.exercicio.mspedido.model.Pedido;
import com.exercicio.mspedido.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class.getName());
    private final PedidoRepository repository;
    private final RestTemplate restTemplate;

    @Override
    public Page<PedidoDto> findAll(Pageable pagination) {
        return repository.findAll(pagination).map(PedidoDto::new);
    }

    @Override
    public PedidoDto findById(Long id) {
        return repository.findById(id)
                .map(PedidoDto::new)
                .orElseThrow(EntityNotFoundException::new);
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
        var pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        pedido.setDataPedido(pedidoDto.dataPedido());
        pedido.setIdProdutos(pedidoDto.idProdutos());
        pedido.setStatus(pedidoDto.status());
        return new PedidoDto(repository.save(pedido));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void atualizaStatus(Long id, StatusPedidoEnum status) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (status == StatusPedidoEnum.CONFIRMADO && pedido.getStatus() != StatusPedidoEnum.CONFIRMADO) {
            pedido.setStatus(status);
            repository.save(pedido);

            try {
                String url = "lb://msproduto/produtos/baixaEstoque";
                List<Long> produtosParaBaixa = pedido.getIdProdutos();
                restTemplate.put(url, produtosParaBaixa);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Falha ao baixar estoque no msproduto para o pedido {0}: {1}",
                        new Object[]{id, e.getMessage()});
            }
        } else {
            pedido.setStatus(status);
            repository.save(pedido);
        }
    }
}