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

## Referências

### 📚 Livros fundamentais
- **Hexagonal Architecture (Ports & Adapters)**: Alistair Cockburn, 2005. Define portas de entrada/saída e adaptadores simétricos.[web:31]
- **Clean Architecture**: Robert C. Martin (Uncle Bob), 2017. Camadas concêntricas com inversão de dependência.[web:36]
- **Domain-Driven Design (DDD)**: Eric Evans, 2003. Entidades ricas, value objects e bounded contexts.[web:42]

### 📰 Artigos recomendados
- [EngSoftModerna: Arquitetura Hexagonal](https://engsoftmoderna.info/artigos/arquitetura-hexagonal.html) — portas de entrada/saída.[web:17]
- [Dev.to: Arquitetura Hexagonal explicada](https://dev.to/pmafra/arquitetura-hexagonal-explicada-como-transformar-seu-desenvolvimento-de-software-43d9) — isolamento do core.[web:16]
- [Herberto Graca: DDD + Hexagonal + Clean](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/) — package by feature.[web:18]

### 💻 Exemplos open-source
- [Sairyss/domain-driven-hexagon](https://github.com/Sairyss/domain-driven-hexagon) — Java + Hexagonal + DDD.[web:19]
