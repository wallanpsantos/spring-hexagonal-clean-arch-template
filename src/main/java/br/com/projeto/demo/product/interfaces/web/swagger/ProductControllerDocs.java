package br.com.projeto.demo.product.interfaces.web.swagger;

import br.com.projeto.demo.product.interfaces.web.dto.request.CreateProductRequest;
import br.com.projeto.demo.product.interfaces.web.dto.response.ProductDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/**
 * Interface exclusiva para anotações OpenAPI.
 * Mantém o Controller limpo, sem poluição de anotações de documentação.
 */
@Tag(name = "Products", description = "Gerenciamento de produtos")
public interface ProductControllerDocs {

    @Operation(summary = "Criar produto", description = "Cria um novo produto no catálogo")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Payload inválido")
    @ApiResponse(responseCode = "422", description = "Violação de regra de negócio")
    ResponseEntity<ProductDetailResponse> create(CreateProductRequest request);
}
