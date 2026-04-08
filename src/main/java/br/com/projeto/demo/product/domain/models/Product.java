package br.com.projeto.demo.product.domain.models;

import br.com.projeto.demo.product.domain.events.ProductCreatedDomainEvent;
import br.com.projeto.demo.product.domain.exceptions.ProductAlreadyDiscontinuedException;
import br.com.projeto.demo.product.domain.valueobjects.Money;
import br.com.projeto.demo.product.domain.valueobjects.Sku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Entidade rica do domínio. Contém identidade própria e métodos de negócio.
 * NÃO possui anotações de framework (JPA, Jackson, etc.).
 */
public class Product {

    private final UUID id;
    private String name;
    private Sku sku;
    private Money price;
    private boolean discontinued;
    private final List<ProductCreatedDomainEvent> domainEvents = new ArrayList<>();

    private Product(UUID id, String name, Sku sku, Money price) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.discontinued = false;
    }

    public static Product create(String name, Sku sku, Money price) {
        var product = new Product(UUID.randomUUID(), name, sku, price);
        product.domainEvents.add(new ProductCreatedDomainEvent(product.id, product.name));
        return product;
    }

    public void updatePrice(Money newPrice) {
        if (this.discontinued) {
            throw new ProductAlreadyDiscontinuedException(this.id);
        }
        this.price = newPrice;
    }

    public void discontinue() {
        if (this.discontinued) {
            throw new ProductAlreadyDiscontinuedException(this.id);
        }
        this.discontinued = true;
    }

    public List<ProductCreatedDomainEvent> pullDomainEvents() {
        var events = List.copyOf(domainEvents);
        domainEvents.clear();
        return events;
    }

    // Getters apenas — sem setters (imutabilidade externa)
    public UUID getId() { return id; }
    public String getName() { return name; }
    public Sku getSku() { return sku; }
    public Money getPrice() { return price; }
    public boolean isDiscontinued() { return discontinued; }
}
