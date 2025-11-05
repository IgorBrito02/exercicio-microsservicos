package com.exercicio.mspagamento.infra.mqueue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.exercicio.mspagamento.dto.PagamentoDto;
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

    @RabbitListener(queues = "${spring.rabbitmq.queue.pagamentos}")
    public void receberMensagemPagamento(@Payload String payload) {
        try {
            JsonNode node = mapper.readTree(payload);

            Long pedidoId = node.get("pedidoId").asLong();
            BigDecimal valor = new BigDecimal(node.get("valor").asText());

            PagamentoDto dto = new PagamentoDto(
                null,
                UUID.randomUUID(),
                pedidoId,
                valor,
                "CONFIRMADO",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24)
            );

            pagamentoService.registrarPagamento(dto);
            log.info("💰 Pagamento processado com sucesso para o pedido {}", pedidoId);

        } catch (JsonProcessingException e) {
            log.error("Erro ao desserializar a mensagem JSON: {}", payload, e);
        } catch (Exception e) {
            log.error("Erro inesperado ao processar pagamento: {}", payload, e);
        }
    }
}