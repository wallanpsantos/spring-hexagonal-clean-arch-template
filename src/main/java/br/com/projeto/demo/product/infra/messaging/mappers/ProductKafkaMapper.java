package br.com.projeto.demo.product.infra.messaging.mappers;

import br.com.projeto.demo.product.domain.events.ProductCreatedDomainEvent;
import br.com.projeto.demo.product.infra.messaging.dto.ProductCreatedKafkaMessage;
import org.mapstruct.Mapper;

/**
 * Mapper MapStruct: converte Domain Event <-> Kafka DTO.
 */
@Mapper(componentModel = "spring")
public interface ProductKafkaMapper {
    ProductCreatedKafkaMessage toKafkaMessage(ProductCreatedDomainEvent event);
}
