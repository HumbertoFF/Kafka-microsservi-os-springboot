### 📦 Microserviços

#### 🛍️ **Produtos** (produtos/)
Responsável pelo gerenciamento do catálogo de produtos.
- CRUD de produtos
- Controle de estoque
- Publicação de eventos de produtos (criação, atualização, baixa de estoque)
- Consulta de disponibilidade

#### 👥 **Clientes** (clientes/)
Gerencia o cadastro e informações dos clientes.
- CRUD de clientes
- Validação de dados cadastrais
- Publicação de eventos de clientes (cadastro, atualização)
- Histórico de compras

#### 📋 **Pedidos** (pedidos/)
Orquestra o processo de criação e gerenciamento de pedidos.
- Criação de pedidos
- Validação de estoque (consome eventos de Produtos)
- Validação de cliente (consome eventos de Clientes)
- Cálculo de valores
- Atualização de status do pedido
- Publicação de eventos de pedidos

#### 🔧 **iCompras-Servicos** (icompras-servicos/)
Módulo compartilhado com utilitários e configurações comuns.
- Configurações do Kafka
- DTOs compartilhados
- Utilitários e helpers
- Constantes e enums do sistema

---

## 🔑 Princípios e Padrões Aplicados

### ✅ Arquitetura
- **Database per Service**: Cada microserviço possui seu banco de dados isolado
- **Event-Driven Architecture**: Comunicação assíncrona orientada a eventos
- **Domain-Driven Design**: Separação clara de contextos delimitados
- **API First**: Endpoints REST bem definidos

### ✅ Comunicação
- **Publish/Subscribe Pattern**: Via Apache Kafka
- **Event Sourcing**: Registro de eventos de domínio
- **Eventual Consistency**: Consistência eventual entre serviços

### ✅ Qualidade
- **Loose Coupling**: Baixo acoplamento entre serviços
- **High Cohesion**: Alta coesão dentro de cada serviço
- **Single Responsibility**: Cada serviço com responsabilidade única
- **Scalability**: Serviços podem escalar independentemente

---

## 🛠️ Stack Tecnológica

### Backend
- **Java 17+** - Linguagem de programação principal
- **Spring Boot 3.x** - Framework para microserviços
- **Spring Data JPA** - Persistência de dados
- **Spring Kafka** - Integração com Apache Kafka

### Mensageria
- **Apache Kafka** - Message broker para comunicação assíncrona
- **Kafka Connect** - Integração com sistemas externos

### Banco de Dados
- **Supabase (PostgreSQL)** - Banco de dados gerenciado para cada microserviço
- **Flyway/Liquibase** - Versionamento de banco de dados

### DevOps
- **Docker** - Containerização
- **Docker Compose** - Orquestração local
- **Maven** - Build e gerenciamento de dependências

---

---

## 🚀 Como Executar

### Pré-requisitos

```bash
# Verificar versões
java -version    # Java 17+
mvn -version     # Maven 3.8+
docker --version # Docker 20+
```

### Configuração do Ambiente

1. **Clone o repositório**
```bash
git clone https://github.com/HumbertoFF/Kafka-microsservi-os-springboot.git
cd Kafka-microsservi-os-springboot
```

2. **Configure o Supabase**

Crie três projetos no Supabase (um para cada microserviço) e configure as variáveis de ambiente:

```properties
# clientes/src/main/resources/application.yml
spring:
  datasource:
    url: jdbc:postgresql://[SEU_SUPABASE_URL]:5432/postgres
    username: postgres
    password: [SUA_SENHA]
```

3. **Suba o Kafka com Docker**

```bash
docker-compose up -d
```

```yaml
# docker-compose.yml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    ports:
      - "9092:9092"
```

4. **Build do módulo compartilhado**

```bash
cd icompras-servicos
mvn clean install
cd ..
```

5. **Execute os microserviços**

```bash
# Terminal 1 - Produtos
cd produtos
mvn spring-boot:run

# Terminal 2 - Clientes
cd clientes
mvn spring-boot:run

# Terminal 3 - Pedidos
cd pedidos
mvn spring-boot:run
```

### 🌐 Endpoints Disponíveis

#### Produtos (porta 8081)
```http
GET    /api/produtos          # Lista todos os produtos
GET    /api/produtos/{id}     # Busca produto por ID
POST   /api/produtos          # Cria novo produto
PUT    /api/produtos/{id}     # Atualiza produto
DELETE /api/produtos/{id}     # Remove produto
```

#### Clientes (porta 8082)
```http
GET    /api/clientes          # Lista todos os clientes
GET    /api/clientes/{id}     # Busca cliente por ID
POST   /api/clientes          # Cria novo cliente
PUT    /api/clientes/{id}     # Atualiza cliente
DELETE /api/clientes/{id}     # Remove cliente
```

#### Pedidos (porta 8083)
```http
GET    /api/pedidos           # Lista todos os pedidos
GET    /api/pedidos/{id}      # Busca pedido por ID
POST   /api/pedidos           # Cria novo pedido
PUT    /api/pedidos/{id}      # Atualiza status do pedido
GET    /api/pedidos/cliente/{id} # Pedidos por cliente
```

---

## 🎯 Fluxo de Eventos (Event Flow)

### 📤 Criação de Pedido