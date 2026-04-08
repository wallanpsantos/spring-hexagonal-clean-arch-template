package br.com.projeto.demo.product.interfaces.web.mappers;

import br.com.projeto.demo.product.domain.models.Product;
import br.com.projeto.demo.product.interfaces.web.dto.response.ProductDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct: converte Domain Model <-> DTO da camada web.
 * Isola o domínio dos detalhes de serialização HTTP.
 */
@Mapper(componentModel = "spring")
public interface ProductWebMapper {

    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "price.currency", target = "currency")
    @Mapping(source = "sku.value", target = "sku")
    ProductDetailResponse toResponse(Product product);
}
