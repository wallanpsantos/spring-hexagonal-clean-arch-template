# Arquitetura — Descrição Detalhada

## Visão Geral

Este template é orientado a **subdomínios**: cada feature/bounded context é uma pasta raiz (ex: `product/`), contendo internamente as 4 camadas da arquitetura. Isso evita o anti-pattern de organizar por tipo técnico (`controllers/`, `services/`, `repositories/` globais).

```
product/
├── domain/        # 1. CORE
├── app/           # 2. ORQUESTRAÇÃO
├── interfaces/    # 3. DRIVING ADAPTERS
└── infra/         # 4. DRIVEN ADAPTERS
```

---

## 1. `domain/` — Core

> **Regra de ouro:** esta camada NÃO pode importar nada de `app/`, `interfaces/` ou `infra/`.

- `models/` — Entidades ricas (com métodos de negócio) e Agregados.
- `valueobjects/` — Objetos imutáveis sem identidade própria (ex: `Money`, `Sku`).
- `services/` — Domain Services: regras que envolvem múltiplas entidades.
- `exceptions/` — Exceções de violação de regra de negócio.
- `events/` — Definição de Domain Events (interfaces ou records puros).
- `factories/` — Lógica de construção complexa de entidades do domínio.

---

## 2. `app/` — Orquestração

> Coordena o fluxo, mas não dita a regra de negócio. Depende apenas de `domain/`.

- `usecases/` — 1 classe = 1 intenção do usuário (ex: `CreateProductUseCase`).
- `ports/in/` — Contratos que os Use Cases implementam (útil quando múltiplos adapters de entrada chamam o mesmo UC via interface).
- `ports/out/repository/` — Contrato para persistência.
- `ports/out/integration/` — Contrato para chamadas a APIs externas.
- `ports/out/messaging/` — Contrato para publicação de eventos.

---

## 3. `interfaces/` — Driving Adapters

> O mundo externo chama a nossa aplicação.

### `web/`
- `controllers/` — Recebe requisição, valida (Bean Validation), chama o Use Case.
- `dto/request/` — Payloads de entrada.
- `dto/response/` — Payloads de saída.
- `mappers/` — Conversores DTO ↔ Domain Model (MapStruct).
- `advice/` — `@RestControllerAdvice`: converte exceções do `domain/` em respostas HTTP.
- `swagger/` — Interfaces com anotações OpenAPI para manter os Controllers limpos.

### `messaging/`
- `consumers/` — Listeners Kafka/RabbitMQ.
- `dto/` — Formato da mensagem recebida.
- `mappers/` — Converte mensagem → chamada ao Use Case.

---

## 4. `infra/` — Driven Adapters

> A nossa aplicação chama o mundo externo.

### `persistence/`
- `entities/` — JPA Entities (`@Entity`, `@Table`).
- `repositories/` — Interfaces Spring Data JPA.
- `adapters/` — Implementa `ports/out/repository`. Faz a ponte entre Spring Data e o domínio.
- `mappers/` — Converte Domain Model ↔ JpaEntity.

### `feign/`
- `clients/` — Interfaces `@FeignClient`.
- `dto/` — DTOs específicos da API externa.
- `adapters/` — Implementa `ports/out/integration`.
- `mappers/` — Converte Feign DTO ↔ Domain Model.
- `exceptions/` — `ErrorDecoder`: trata timeouts, 404s e 500s da API externa.

### `messaging/`
- `publishers/` — Implementa `ports/out/messaging`. Usa `KafkaTemplate`.
- `dto/` — Formato do evento serializado (JSON/Avro).
- `mappers/` — Converte Domain Event ↔ Kafka DTO.

### `config/`
- `beans/` — `@Configuration` que instancia Use Cases injetando os Adapters. **Aqui mora a Inversão de Dependência.**
- `security/` — OAuth2, JWT, CORS.
- `kafka/` — Producers, Consumers, Tópicos, DLQs, retentativas.
- `database/` — Flyway/Liquibase, Datasource secundário.
- `swagger/` — Configuração geral do OpenAPI.
