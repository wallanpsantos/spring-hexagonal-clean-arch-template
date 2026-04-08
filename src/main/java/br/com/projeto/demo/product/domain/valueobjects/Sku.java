package br.com.projeto.demo.product.domain.valueobjects;

/**
 * Value Object: representa o código SKU do produto.
 */
public record Sku(String value) {

    public Sku {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SKU não pode ser vazio.");
        }
        if (!value.matches("[A-Z0-9\\-]{3,20}")) {
            throw new IllegalArgumentException("SKU inválido: deve conter apenas letras maiúsculas, números e hífens (3-20 caracteres).");
        }
    }
}
