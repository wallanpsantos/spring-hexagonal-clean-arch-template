package br.com.projeto.demo.product.infra.persistence.repositories;

import br.com.projeto.demo.product.infra.persistence.entities.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface Spring Data JPA.
 * Conhecida apenas pelo Adapter de persistência — nunca exposta ao domínio.
 */
public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, UUID> {
    Optional<ProductJpaEntity> findBySku(String sku);
    boolean existsBySku(String sku);
}
