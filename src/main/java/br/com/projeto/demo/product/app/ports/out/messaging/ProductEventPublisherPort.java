package br.com.projeto.demo.product.app.ports.out.messaging;

import br.com.projeto.demo.product.domain.events.ProductCreatedDomainEvent;

/**
 * Porta de SAÍDA (Outbound Port): contrato para publicação de eventos.
 * A implementação real fica em infra/messaging/publishers/.
 */
public interface ProductEventPublisherPort {
    void publish(ProductCreatedDomainEvent event);
}
