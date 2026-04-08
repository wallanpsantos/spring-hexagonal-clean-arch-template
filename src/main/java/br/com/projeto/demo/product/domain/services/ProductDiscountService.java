package br.com.projeto.demo.product.domain.services;

import br.com.projeto.demo.product.domain.models.Product;
import br.com.projeto.demo.product.domain.valueobjects.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Domain Service: regra de negócio que envolve múltiplas entidades
 * ou que não pertence naturalmente a nenhuma entidade específica.
 */
public class ProductDiscountService {

    public void applyPercentageDiscount(Product product, BigDecimal percentageDiscount) {
        if (percentageDiscount.compareTo(BigDecimal.ZERO) <= 0
                || percentageDiscount.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Desconto deve estar entre 1% e 100%.");
        }
        var factor = BigDecimal.ONE.subtract(
                percentageDiscount.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
        );
        var discountedAmount = product.getPrice().amount().multiply(factor).setScale(2, RoundingMode.HALF_UP);
        product.updatePrice(new Money(discountedAmount, product.getPrice().currency()));
    }
}
