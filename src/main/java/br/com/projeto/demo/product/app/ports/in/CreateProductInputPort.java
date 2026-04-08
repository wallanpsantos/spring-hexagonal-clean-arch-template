package br.com.projeto.demo.product.app.ports.in;

import br.com.projeto.demo.product.domain.models.Product;

import java.math.BigDecimal;

/**
 * Porta de ENTRADA (Inbound Port): contrato que o Use Case implementa.
 * Útil quando múltiplos adapters de entrada (HTTP + Kafka consumer)
 * precisam chamar o mesmo Use Case via interface, evitando acoplamento direto.
 */
public interface CreateProductInputPort {
    Product execute(String name, String sku, BigDecimal price);
}
