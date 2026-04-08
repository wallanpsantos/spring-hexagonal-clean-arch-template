package br.com.projeto.demo.product.domain.factories;

import br.com.projeto.demo.product.domain.models.Product;
import br.com.projeto.demo.product.domain.valueobjects.Money;
import br.com.projeto.demo.product.domain.valueobjects.Sku;

import java.math.BigDecimal;

/**
 * Factory: encapsula lógica de construção complexa de entidades do domínio.
 * Uso recomendado quando a criação envolve mais do que simples atribuição.
 */
public class ProductFactory {

    public static Product createWithDefaultCurrency(String name, String skuValue, BigDecimal price) {
        return Product.create(
                name,
                new Sku(skuValue),
                Money.of(price, "BRL")
        );
    }
}
