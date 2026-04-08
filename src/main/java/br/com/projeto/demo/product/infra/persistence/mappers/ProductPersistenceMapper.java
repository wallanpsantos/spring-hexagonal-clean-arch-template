package br.com.projeto.demo.product.infra.persistence.mappers;

import br.com.projeto.demo.product.domain.models.Product;
import br.com.projeto.demo.product.infra.persistence.entities.ProductJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct: converte Domain Model <-> JpaEntity.
 * Garante que o banco não conhece o domínio e o domínio não conhece o banco.
 */
@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "price.currency", target = "currency")
    @Mapping(source = "sku.value", target = "sku")
    ProductJpaEntity toEntity(Product product);

    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "currency", target = "price.currency")
    @Mapping(source = "sku", target = "sku.value")
    Product toDomain(ProductJpaEntity entity);
}
