package br.com.projeto.demo.product.infra.feign.clients;

import br.com.projeto.demo.product.infra.feign.dto.response.TaxCalculationFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Client Feign: interface para integração HTTP com o serviço externo de cálculo de impostos.
 * Conhecida apenas pelo Adapter de integração.
 */
@FeignClient(name = "tax-calculation-service", url = "${integration.tax-service.url}")
public interface TaxCalculationFeignClient {

    @GetMapping("/taxes")
    TaxCalculationFeignResponse calculateTax(
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("category") String category
    );
}
