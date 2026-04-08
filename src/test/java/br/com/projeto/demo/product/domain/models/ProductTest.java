package br.com.projeto.demo.product.domain.models;

import br.com.projeto.demo.product.domain.exceptions.ProductAlreadyDiscontinuedException;
import br.com.projeto.demo.product.domain.valueobjects.Money;
import br.com.projeto.demo.product.domain.valueobjects.Sku;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.*;

/**
 * Testes unitários do domínio: sem Spring, sem mocks de infra.
 * Testam apenas regras de negócio puras.
 */
class ProductTest {

    private static final Money BRL_100 = new Money(BigDecimal.valueOf(100), Currency.getInstance("BRL"));
    private static final Sku VALID_SKU = new Sku("PROD-001");

    @Test
    void shouldCreateProductWithDomainEvent() {
        var product = Product.create("Notebook", VALID_SKU, BRL_100);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Notebook");
        assertThat(product.isDiscontinued()).isFalse();

        var events = product.pullDomainEvents();
        assertThat(events).hasSize(1);
        assertThat(events.get(0).productId()).isEqualTo(product.getId());
    }

    @Test
    void shouldUpdatePriceWhenNotDiscontinued() {
        var product = Product.create("Notebook", VALID_SKU, BRL_100);
        var newPrice = new Money(BigDecimal.valueOf(90), Currency.getInstance("BRL"));

        product.updatePrice(newPrice);

        assertThat(product.getPrice().amount()).isEqualByComparingTo(BigDecimal.valueOf(90));
    }

    @Test
    void shouldThrowWhenUpdatingPriceOfDiscontinuedProduct() {
        var product = Product.create("Notebook", VALID_SKU, BRL_100);
        product.discontinue();

        assertThatThrownBy(() -> product.updatePrice(BRL_100))
                .isInstanceOf(ProductAlreadyDiscontinuedException.class);
    }

    @Test
    void shouldThrowWhenDiscontinuingAlreadyDiscontinuedProduct() {
        var product = Product.create("Notebook", VALID_SKU, BRL_100);
        product.discontinue();

        assertThatThrownBy(product::discontinue)
                .isInstanceOf(ProductAlreadyDiscontinuedException.class);
    }
}
