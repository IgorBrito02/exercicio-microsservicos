package com.exercicio.mspagamento.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queuePagamentos() {
        return new Queue("queue_pagamentos", true);
    }
}