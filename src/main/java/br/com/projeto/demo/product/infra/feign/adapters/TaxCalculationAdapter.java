package br.com.projeto.demo.product.infra.feign.adapters;

import br.com.projeto.demo.product.app.ports.out.integration.TaxCalculationPort;
import br.com.projeto.demo.product.domain.valueobjects.Money;
import br.com.projeto.demo.product.infra.feign.clients.TaxCalculationFeignClient;
import br.com.projeto.demo.product.infra.feign.mappers.TaxCalculationFeignMapper;

/**
 * Driven Adapter de integração: implementa TaxCalculationPort.
 * Chama o Feign Client e converte o resultado para o domínio.
 */
public class TaxCalculationAdapter implements TaxCalculationPort {

    private final TaxCalculationFeignClient feignClient;
    private final TaxCalculationFeignMapper mapper;

    public TaxCalculationAdapter(TaxCalculationFeignClient feignClient, TaxCalculationFeignMapper mapper) {
        this.feignClient = feignClient;
        this.mapper = mapper;
    }

    @Override
    public Money calculateTax(Money basePrice, String productCategory) {
        var response = feignClient.calculateTax(
                basePrice.amount().toPlainString(),
                basePrice.currency().getCurrencyCode(),
                productCategory
        );
        return mapper.toDomain(response);
    }
}
