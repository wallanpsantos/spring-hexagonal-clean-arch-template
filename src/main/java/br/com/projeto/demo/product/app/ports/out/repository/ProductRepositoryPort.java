package br.com.projeto.demo.product.app.ports.out.repository;

import br.com.projeto.demo.product.domain.models.Product;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de SAÍDA (Outbound Port): contrato para persistência.
 * O domínio e a aplicação só conhecem esta interface — nunca a implementação JPA.
 */
public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    boolean existsBySku(String sku);
}
