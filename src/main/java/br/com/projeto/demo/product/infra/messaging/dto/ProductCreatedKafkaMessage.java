package br.com.projeto.demo.product.infra.messaging.dto;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de mensageria: formato do evento serializado para o tópico Kafka.
 * Isolado dos DTOs HTTP — cada contexto de comunicação tem seus próprios objetos.
 */
public record ProductCreatedKafkaMessage(
        UUID productId,
        String productName,
        Instant occurredOn
) {}
