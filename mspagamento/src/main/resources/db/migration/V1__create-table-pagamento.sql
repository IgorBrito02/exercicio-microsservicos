CREATE TABLE tb_pagamentos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo CHAR(36) NOT NULL,
    pedido_id BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_expiracao DATETIME NOT NULL
);