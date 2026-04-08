package br.com.projeto.demo.product.infra.persistence.adapters;

import br.com.projeto.demo.product.app.ports.out.repository.ProductRepositoryPort;
import br.com.projeto.demo.product.domain.models.Product;
import br.com.projeto.demo.product.infra.persistence.mappers.ProductPersistenceMapper;
import br.com.projeto.demo.product.infra.persistence.repositories.SpringDataProductRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Driven Adapter de persistência: implementa a porta de saída ProductRepositoryPort.
 * É o único ponto de conversão entre Domain Model e JPA Entity.
 * O Spring injeta este adapter via beans/ProductBeanConfig.
 */
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository springRepository;
    private final ProductPersistenceMapper mapper;

    public ProductPersistenceAdapter(SpringDataProductRepository springRepository,
                                      ProductPersistenceMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var saved = springRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return springRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsBySku(String sku) {
        return springRepository.existsBySku(sku);
    }
}
