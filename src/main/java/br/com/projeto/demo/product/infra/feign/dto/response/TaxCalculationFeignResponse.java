package br.com.projeto.demo.product.infra.feign.dto.response;

import java.math.BigDecimal;

/**
 * DTO específico da API externa de impostos.
 * Isolado dos DTOs HTTP da web/ — evita acoplamento acidental.
 */
public record TaxCalculationFeignResponse(
        BigDecimal taxAmount,
        String currency
) {}
