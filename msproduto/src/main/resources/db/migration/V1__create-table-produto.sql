CREATE TABLE tb_produtos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    quantidade INT NOT NULL,
    descricao TEXT,
    preco FLOAT NOT NULL
)