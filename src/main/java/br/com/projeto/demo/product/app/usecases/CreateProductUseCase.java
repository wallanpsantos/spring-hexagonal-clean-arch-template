package br.com.projeto.demo.product.app.usecases;

import br.com.projeto.demo.product.app.ports.out.messaging.ProductEventPublisherPort;
import br.com.projeto.demo.product.app.ports.out.repository.ProductRepositoryPort;
import br.com.projeto.demo.product.domain.factories.ProductFactory;
import br.com.projeto.demo.product.domain.models.Product;

import java.math.BigDecimal;

/**
 * Use Case: 1 classe = 1 intenção do usuário.
 * Orquestra o fluxo sem conter regras de negócio.
 * Recebe as portas via construtor (Dependency Inversion).
 */
public class CreateProductUseCase {

    private final ProductRepositoryPort repositoryPort;
    private final ProductEventPublisherPort eventPublisherPort;

    public CreateProductUseCase(ProductRepositoryPort repositoryPort,
                                 ProductEventPublisherPort eventPublisherPort) {
        this.repositoryPort = repositoryPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    public Product execute(String name, String sku, BigDecimal price) {
        var product = ProductFactory.createWithDefaultCurrency(name, sku, price);
        repositoryPort.save(product);
        product.pullDomainEvents().forEach(eventPublisherPort::publish);
        return product;
    }
}
