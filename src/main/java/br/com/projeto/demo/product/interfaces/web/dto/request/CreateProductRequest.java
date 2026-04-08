package br.com.projeto.demo.product.interfaces.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO de entrada: Java Record anêmico. Validação via Bean Validation.
 * NÃO possui dependência do domínio.
 */
public record CreateProductRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "SKU é obrigatório")
        String sku,

        @NotNull @Positive(message = "Preço deve ser positivo")
        BigDecimal price
) {}
