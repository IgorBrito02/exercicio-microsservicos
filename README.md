# ☁️ Sistema de Microsserviços – Produtos e Pedidos

API REST desenvolvida para o **gerenciamento de produtos e pedidos**, utilizando a arquitetura de **microsserviços** com **Spring Cloud Netflix Eureka** e **API Gateway**.  
Este projeto foi implementado como parte da disciplina **Microsserviços** do curso de **Pós-Graduação em Desenvolvimento Web Full Stack (Unipê)**.

---

## 🧑‍💻 Desenvolvedores

**Igor Pinheiro de Brito**  
📧 [igor_pbrito@hotmail.com](mailto:igor_pbrito@hotmail.com)  
🔗 [GitHub – IgorBrito02](https://github.com/IgorBrito02)

**Leoncio Fernandes de Oliveira Filho**


---

## 🚀 Tecnologias Utilizadas

| Categoria | Tecnologias |
|------------|--------------|
| **Linguagem** | Java 21 |
| **Framework Principal** | Spring Boot 3.5.6 |
| **Microsserviços** | Spring Cloud 2025.0.0 |
| **Módulos** | Spring Web, Spring Data JPA, Spring Validation |
| **Banco de Dados** | MySQL |
| **Migração de Banco** | Flyway |
| **Descoberta de Serviços** | Netflix Eureka Server & Client |
| **API Gateway** | Spring Cloud Gateway |
| **Anotações Utilitárias** | Lombok |
| **Gerenciador de Dependências** | Maven |
| **IDE Recomendada** | IntelliJ IDEA / STS / VS Code |

---

## ⚙️ Estrutura do Projeto

O sistema é composto por **quatro microsserviços independentes**, comunicando-se via **Eureka Service Discovery**:

| Serviço | Descrição | Porta |
|----------|------------|--------|
| **serviceregistry** | Serviço de Registro (Eureka Server) | `8761` |
| **msproduto** | Gerenciamento de Produtos | Aleatória (`server.port=0`) |
| **mspedido** | Gerenciamento de Pedidos | Aleatória (`server.port=0`) |
| **gateway** | API Gateway para roteamento | `8080` |

---

## 🧭 Ordem Correta de Inicialização

1️⃣ **Service Registry (Eureka)**  
```bash
cd serviceregistry
mvn spring-boot:run
```
➡️ Acesse [http://localhost:8761](http://localhost:8761)  
Você verá o painel do Eureka Server.

---

2️⃣ **Microsserviço de Produtos**
```bash
cd ../msproduto
mvn spring-boot:run
```
Verifique no console: `Registered instance msproduto with status UP`

---

3️⃣ **Microsserviço de Pedidos**
```bash
cd ../mspedido
mvn spring-boot:run
```

---

4️⃣ **API Gateway**
```bash
cd ../gateway
mvn spring-boot:run
```
➡️ Após todos os serviços iniciarem, o painel do Eureka mostrará:

```
serviceregistry | UP
msproduto       | UP
mspedido        | UP
gateway         | UP
```

---

## 🌐 Endpoints Principais (via Gateway)

### 🧱 PRODUTOS

| Método | Endpoint | Descrição |
|---------|-----------|------------|
| GET | `/msproduto/produtos` | Lista todos os produtos |
| GET | `/msproduto/produtos/{id}` | Busca produto por ID |
| POST | `/msproduto/produtos` | Cria um novo produto |
| PUT | `/msproduto/produtos/{id}` | Atualiza um produto existente |
| DELETE | `/msproduto/produtos/{id}` | Remove um produto |

**Exemplo de criação (POST):**
```json
{
  "nome": "Teclado Mecânico",
  "descricao": "Teclado RGB, switch blue",
  "quantidade": 3,
  "preco": 250.0
}
```

**Retorno esperado (201 Created):**
```json
{
  "id": 2,
  "nome": "Teclado Mecânico",
  "quantidade": 3,
  "descricao": "Teclado RGB, switch blue",
  "preco": 250.0
}
```

---

### 📦 PEDIDOS

| Método | Endpoint | Descrição |
|---------|-----------|------------|
| GET | `/mspedido/pedidos` | Lista todos os pedidos |
| GET | `/mspedido/pedidos/{id}` | Busca pedido por ID |
| POST | `/mspedido/pedidos` | Cria um novo pedido |
| PUT | `/mspedido/pedidos/{id}/status?status=CONFIRMADO` | Atualiza o status do pedido |
| DELETE | `/mspedido/pedidos/{id}` | Remove um pedido |

**Exemplo de criação (POST):**
```json
{
  "idProdutos": [1, 2]
}
```

**Retorno esperado:**
```json
{
  "id": 1,
  "dataPedido": "2025-10-27T21:51:42.574784",
  "status": "CRIADO",
  "idProdutos": [1, 2]
}
```

**Atualização de status (PUT):**
```bash
PUT /mspedido/pedidos/1/status?status=CONFIRMADO
```
✅ Retorna `204 No Content`.

---

## 🧪 Testes Realizados com Sucesso (2025-10-27)

Todos os endpoints foram testados via **Postman** através do **Gateway (porta 8080)**.  
Os retornos HTTP confirmaram a funcionalidade completa do ecossistema.

| Operação | Endpoint | Status HTTP |
|-----------|-----------|--------------|
| Criar Produto | POST /msproduto/produtos | ✅ 201 Created |
| Listar Produtos | GET /msproduto/produtos | ✅ 200 OK |
| Criar Pedido | POST /mspedido/pedidos | ✅ 201 Created |
| Listar Pedidos | GET /mspedido/pedidos | ✅ 200 OK |
| Atualizar Status do Pedido | PUT /mspedido/pedidos/{id}/status | ✅ 204 No Content |

---

## 🧱 Requisitos Técnicos Atendidos

- [x] Arquitetura de microsserviços completa (Eureka + Gateway + 2 APIs)
- [x] Comunicação entre serviços via Service Discovery
- [x] CRUD completo para Produto e Pedido
- [x] Persistência em MySQL com migração Flyway
- [x] Tratamento global de exceções
- [x] Configuração isolada de `application.yml` por serviço
- [x] Testes reais executados e validados
