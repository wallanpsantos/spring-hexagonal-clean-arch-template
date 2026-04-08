package br.com.projeto.demo.product.domain.events;

import java.time.Instant;
import java.util.UUID;

/**
 * Domain Event: representa um fato relevante que ocorreu no domínio.
 * Record puro — sem dependência de framework.
 */
public record ProductCreatedDomainEvent(
        UUID productId,
        String productName,
        Instant occurredOn
) {
    public ProductCreatedDomainEvent(UUID productId, String productName) {
        this(productId, productName, Instant.now());
    }
}
