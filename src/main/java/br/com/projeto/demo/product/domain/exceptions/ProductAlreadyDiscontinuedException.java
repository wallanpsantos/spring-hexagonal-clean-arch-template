package br.com.projeto.demo.product.domain.exceptions;

import java.util.UUID;

/**
 * Exceção de violação de regra de negócio.
 * Não carróão de stack trace (performance) — use para fluxos esperados.
 */
public class ProductAlreadyDiscontinuedException extends RuntimeException {

    public ProductAlreadyDiscontinuedException(UUID productId) {
        super("Produto já foi descontinuado: %s".formatted(productId));
    }
}
