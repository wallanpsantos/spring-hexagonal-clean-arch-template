package br.com.projeto.demo.product.infra.messaging.publishers;

import br.com.projeto.demo.product.app.ports.out.messaging.ProductEventPublisherPort;
import br.com.projeto.demo.product.domain.events.ProductCreatedDomainEvent;
import br.com.projeto.demo.product.infra.messaging.dto.ProductCreatedKafkaMessage;
import br.com.projeto.demo.product.infra.messaging.mappers.ProductKafkaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Driven Adapter de mensageria: implementa ProductEventPublisherPort.
 * Converte Domain Event -> Kafka DTO e publica no tópico.
 */
public class ProductKafkaPublisher implements ProductEventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(ProductKafkaPublisher.class);

    private final KafkaTemplate<String, ProductCreatedKafkaMessage> kafkaTemplate;
    private final ProductKafkaMapper mapper;
    private final String topic;

    public ProductKafkaPublisher(KafkaTemplate<String, ProductCreatedKafkaMessage> kafkaTemplate,
                                  ProductKafkaMapper mapper,
                                  String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
        this.topic = topic;
    }

    @Override
    public void publish(ProductCreatedDomainEvent event) {
        var message = mapper.toKafkaMessage(event);
        kafkaTemplate.send(topic, message.productId().toString(), message);
        log.info("[Kafka] Evento publicado: topic={}, productId={}", topic, message.productId());
    }
}
