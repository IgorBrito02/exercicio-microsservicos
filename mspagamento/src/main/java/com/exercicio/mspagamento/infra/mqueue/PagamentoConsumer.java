package com.exercicio.mspagamento.infra.mqueue;

import java.math.BigDecimal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.exercicio.mspagamento.service.PagamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;
    private final ObjectMapper mapper;

    @SuppressWarnings("UnusedAssignment")
    @RabbitListener(queues = "${spring.rabbitmq.queue.pagamentos}")
    public void receberMensagemPagamento(@Payload String payload) {

        Long pedidoId = null;
        BigDecimal valor = null;

        try {
            JsonNode node = mapper.readTree(payload);
            
            pedidoId = node.get("pedidoId").asLong();
            valor = new BigDecimal(node.get("valor").asText());
            
            pagamentoService.registrarPagamento(pedidoId, valor);
            
            log.info("💰 Pagamento processado com sucesso para o pedido {}", pedidoId);
            
        }

        catch (JsonProcessingException e) {
            log.error("Erro ao desserializar a mensagem JSON: {}", payload, e);
        } catch (Exception e) {
            log.error("Erro inesperado ao processar pagamento do pedido {}: {}", pedidoId, payload, e);
        }
    }
}