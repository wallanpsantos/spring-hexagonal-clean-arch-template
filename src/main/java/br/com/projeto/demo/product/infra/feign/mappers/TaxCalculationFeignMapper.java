package br.com.projeto.demo.product.infra.feign.mappers;

import br.com.projeto.demo.product.domain.valueobjects.Money;
import br.com.projeto.demo.product.infra.feign.dto.response.TaxCalculationFeignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper MapStruct: converte Feign DTO <-> Domain Value Object.
 */
@Mapper(componentModel = "spring")
public interface TaxCalculationFeignMapper {

    @Mapping(source = "taxAmount", target = "amount")
    @Mapping(source = "currency", target = "currency")
    Money toDomain(TaxCalculationFeignResponse response);
}
