# Spring Boot — Hexagonal Architecture Template

Template de arquitetura Java 21 + Spring Boot orientado a subdomínios, traduzindo fielmente os princípios da **Hexagonal Architecture (Ports & Adapters)** e **Clean Architecture**.

## Camadas

| Camada | Pacote | Responsabilidade |
|---|---|---|
| Core | `domain/` | Regras de negócio puras. Sem dependências externas. |
| Orquestração | `app/` | Coordena o fluxo via Use Cases. Depende apenas de `domain/`. |
| Driving Adapters | `interfaces/` | O mundo externo chamando a aplicação (HTTP, Kafka consumer). |
| Driven Adapters | `infra/` | A aplicação chamando o mundo externo (JPA, Feign, Kafka producer). |

## Regra da Dependência

```
domain ← app ← interfaces
               ↑
             infra
```

> O `domain` não conhece ninguém. O `infra` conhece todos. As setas indicam direção de dependência.

## Stack

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- OpenFeign
- Apache Kafka
- MapStruct
- Flyway
- OpenAPI 3 (Swagger)

## Como usar

Clone o repositório, substitua o pacote base `br.com.projeto.demo` e o módulo `product` pelo seu domínio.

```bash
git clone https://github.com/wallanpsantos/spring-hexagonal-clean-arch-template.git
```

## Estrutura de Pacotes

Ver [ARCHITECTURE.md](./ARCHITECTURE.md) para descrição detalhada de cada pacote.
