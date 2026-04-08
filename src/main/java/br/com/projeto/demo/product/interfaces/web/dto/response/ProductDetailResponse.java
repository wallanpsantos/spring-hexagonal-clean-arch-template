package br.com.projeto.demo.product.interfaces.web.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO de saída: Java Record. Não expõe detalhes internos do domínio.
 */
public record ProductDetailResponse(
        UUID id,
        String name,
        String sku,
        BigDecimal price,
        String currency,
        boolean discontinued
) {}
