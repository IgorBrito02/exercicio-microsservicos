# 💰 Sistema de Pagamentos – Microsserviços

API REST desenvolvida para o gerenciamento e processamento de pagamentos de pedidos em um ecossistema de microsserviços.
Este projeto corresponde à **Segunda Entrega** do exercício da disciplina **Microsserviços**, do curso de Pós-Graduação em Desenvolvimento Web Full Stack (Unipê).

---

## 👨‍💻 Desenvolvedores

| Nome | E-mail | GitHub |
|------|---------|--------|
| Igor Pinheiro de Brito | igor_pbrito@hotmail.com | [IgorBrito02](https://github.com/IgorBrito02) |
| Leoncio Fernandes de Oliveira Filho | leonciofernandes@gmail.com | [LeoncioFernandes](https://github.com/LeoncioFernandes) |

---

## 🚀 Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|--------------|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.5.7 |
| Módulos do Spring | Spring Web, Spring Data JPA, Spring Boot DevTools, Validation |
| Comunicação Assíncrona | RabbitMQ (Producer/Consumer) |
| Comunicação Síncrona | RestTemplate com Load Balancer |
| Banco de Dados | MySQL (produção) |
| Migrações | Flyway |
| Descoberta de Serviços | Eureka Server / Client |
| Gerenciamento de Dependências | Maven |
| IDE Recomendada | VS Code / IntelliJ IDEA / STS |

---

## ⚙️ Microsserviços Implementados

| Serviço | Porta | Função |
|----------|--------|--------|
| **Service Registry (Eureka)** | 8761 | Registro e descoberta de serviços |
| **API Gateway** | 8080 | Roteamento das requisições (`/pedidos` e `/produtos`) |
| **msproduto** | 8081 | Gerenciamento de produtos |
| **mspedido** | 8082 | Criação e controle de pedidos |
| **mspagamento** | 8083 | Processamento e registro de pagamentos |

---

## 💡 Funcionalidades Implementadas

✅ Registro de pagamentos via API REST
✅ Processamento assíncrono de mensagens de pagamento via RabbitMQ
✅ Atualização automática do status do pedido após confirmação do pagamento
✅ Criação automática do pagamento com status `CONFIRMADO` e expiração de 24h
✅ Integração completa entre os microsserviços (Pedidos, Produtos e Pagamentos)
✅ Estrutura organizada em camadas: `controller`, `service`, `repository`, `dto`, `model` e `infra.mqueue`
✅ Banco de dados criado via Flyway (`tb_pagamentos`)
✅ Registro no Eureka Server
✅ Testes de execução bem-sucedidos em todos os microsserviços

---

## 🧭 Ordem Correta de Inicialização

```bash
cd serviceregistry
mvn spring-boot:run

cd msproduto
mvn spring-boot:run

cd mspedido
mvn spring-boot:run

cd mspagamento
mvn spring-boot:run

cd gateway
mvn spring-boot:run
```

🔹 Essa é a ordem recomendada para garantir que o **Eureka Server** já esteja ativo antes dos clientes (msproduto, mspedido, mspagamento) e que o **Gateway** reconheça corretamente os serviços registrados.

---

## 🧪 Execução e Testes

### 1️⃣ Subir o RabbitMQ
```bash
docker run -d --hostname rabbitmq-host --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
Acesse: [http://localhost:15672](http://localhost:15672)
Usuário: `guest` | Senha: `guest`

### 2️⃣ Testar no Postman
- **Criar Produto** → `POST http://localhost:8081/produtos`
- **Criar Pedido** → `POST http://localhost:8082/pedidos`
- **Pagamento (assíncrono)** é processado via RabbitMQ e salvo no `mspagamento`
- **Consultar Pagamento** → `GET http://localhost:8083/pagamentos/{id}`

---

## 📁 Estrutura de Pastas (Resumo)

```
EXERCICIO-MICROSSERVICOS/
│
├── gateway/
├── mspagamento/
│   ├── config/
│   ├── controller/
│   ├── dto/
│   ├── infra\mqueue/
│   ├── model/
│   ├── repository/
│   └── service/
├── mspedido/
│   ├── controller/
│   ├── dto/
│   ├── enums/
│   ├── exception/
│   ├── model/
│   ├── repository/
│   └── service/
├── msproduto/
│   ├── controller/
│   ├── dto/
│   ├── exception/
│   ├── model/
│   ├── repository/
│   └── service/
└── serviceregistry/
```

---

## 🧱 Requisitos Técnicos Atendidos
- [x] API de Pagamentos criada conforme enunciado
- [x] Integração síncrona e assíncrona entre microsserviços
- [x] Uso de RabbitMQ e Eureka
- [x] Camadas e pacotes padronizados
- [x] Migrações Flyway
- [x] Banco MySQL funcionando
- [x] Testes práticos executados com sucesso

---

## 🧩 Execução do Projeto (Resumo)

```bash
git clone --branch exercicio-parte-2 --single-branch https://github.com/IgorBrito02/exercicio-microsservicos.git
cd exercicio-microsservicos
mvn clean spring-boot:run
```

Acesse o Eureka em: 👉 [http://localhost:8761](http://localhost:8761)

---

© 2025 – Desenvolvido por **Igor Pinheiro de Brito** e **Leoncio Fernandes de Oliveira Filho**.