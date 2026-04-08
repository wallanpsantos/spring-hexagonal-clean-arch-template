package br.com.projeto.demo.product.interfaces.messaging.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Driving Adapter assíncrono: consome mensagens Kafka e delega ao Use Case.
 * NÃO contém regra de negócio.
 */
@Component
public class CategoryUpdatedKafkaListener {

    private static final Logger log = LoggerFactory.getLogger(CategoryUpdatedKafkaListener.class);

    // TODO: injetar o Use Case via construtor
    @KafkaListener(topics = "${kafka.topics.category-updated}", groupId = "${spring.kafka.consumer.group-id}")
    public void onCategoryUpdated(String message) {
        log.info("[Kafka] Mensagem recebida no tópico category-updated: {}", message);
        // TODO: deserializar para DTO, mapear e chamar o Use Case
    }
}
