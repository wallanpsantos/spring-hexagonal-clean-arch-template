package br.com.projeto.demo.product.infra.config.beans;

import br.com.projeto.demo.product.app.usecases.CreateProductUseCase;
import br.com.projeto.demo.product.infra.messaging.mappers.ProductKafkaMapper;
import br.com.projeto.demo.product.infra.messaging.publishers.ProductKafkaPublisher;
import br.com.projeto.demo.product.infra.persistence.adapters.ProductPersistenceAdapter;
import br.com.projeto.demo.product.infra.persistence.mappers.ProductPersistenceMapper;
import br.com.projeto.demo.product.infra.persistence.repositories.SpringDataProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Ponto central da Inversão de Dependência.
 * O Spring Framework fica ISOLADO aqui: injeta os adapters nos Use Cases.
 * Os Use Cases não sabem que o Spring existe.
 */
@Configuration
public class ProductBeanConfig {

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(
            SpringDataProductRepository springRepository,
            ProductPersistenceMapper mapper) {
        return new ProductPersistenceAdapter(springRepository, mapper);
    }

    @Bean
    public ProductKafkaPublisher productKafkaPublisher(
            KafkaTemplate<String, ?> kafkaTemplate,
            ProductKafkaMapper mapper,
            @Value("${kafka.topics.product-created}") String topic) {
        return new ProductKafkaPublisher((KafkaTemplate) kafkaTemplate, mapper, topic);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductPersistenceAdapter persistenceAdapter,
            ProductKafkaPublisher kafkaPublisher) {
        return new CreateProductUseCase(persistenceAdapter, kafkaPublisher);
    }
}
