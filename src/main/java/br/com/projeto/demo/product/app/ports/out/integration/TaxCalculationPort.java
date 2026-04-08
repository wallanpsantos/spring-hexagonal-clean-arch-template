package br.com.projeto.demo.product.app.ports.out.integration;

import br.com.projeto.demo.product.domain.valueobjects.Money;

/**
 * Porta de SAÍDA (Outbound Port): contrato para chamadas a APIs externas.
 * A implementação real fica em infra/feign/adapters/.
 */
public interface TaxCalculationPort {
    Money calculateTax(Money basePrice, String productCategory);
}
