package com.exercicio.mspagamento.service;

import com.exercicio.mspagamento.dto.PagamentoDto;
import com.exercicio.mspagamento.model.Pagamento;
import com.exercicio.mspagamento.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;

    @Transactional
    @Override
    public PagamentoDto registrarPagamento(Long pedidoId, BigDecimal valor) {
        Pagamento pagamento = Pagamento.novoPagamento(pedidoId, valor);
        return new PagamentoDto(repository.save(pagamento));
    }
}