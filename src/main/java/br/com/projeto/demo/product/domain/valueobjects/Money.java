package br.com.projeto.demo.product.domain.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Value Object: imutável, sem identidade própria.
 * A igualdade é baseada nos atributos, não na referência.
 */
public record Money(BigDecimal amount, Currency currency) {

    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor monetário não pode ser nulo ou negativo.");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Moeda não pode ser nula.");
        }
    }

    public static Money of(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    private void assertSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Não é possível operar moedas diferentes: %s e %s"
                    .formatted(this.currency, other.currency));
        }
    }
}
