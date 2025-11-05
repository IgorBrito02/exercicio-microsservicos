package com.exercicio.mspagamento.service;

import com.exercicio.mspagamento.dto.PagamentoDto;
import com.exercicio.mspagamento.model.Pagamento;
import com.exercicio.mspagamento.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private static final Logger LOGGER = Logger.getLogger(PagamentoServiceImpl.class.getName());
    private final PagamentoRepository repository;
    private final RestTemplate restTemplate;

    @Transactional
    @Override
    public PagamentoDto registrarPagamento(PagamentoDto dto) {
        Pagamento pagamento = Pagamento.novoPagamento(dto.pedidoId(), dto.valor());
        Pagamento pagamentoSalvo = repository.save(pagamento);

        try {
            String url = "lb://mspedido/pedidos/" + pagamentoSalvo.getPedidoId() + "/status";
            restTemplate.put(url + "?status=CONFIRMADO", null);
        } catch (Exception e) {
            LOGGER.warning("Falha ao notificar mspedido: " + e.getMessage());
        }

        return new PagamentoDto(pagamentoSalvo);
    }

    @Override
    public PagamentoDto buscarPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        return new PagamentoDto(pagamento);
    }
}