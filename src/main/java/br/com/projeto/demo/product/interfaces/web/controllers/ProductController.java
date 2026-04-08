package br.com.projeto.demo.product.interfaces.web.controllers;

import br.com.projeto.demo.product.app.usecases.CreateProductUseCase;
import br.com.projeto.demo.product.interfaces.web.dto.request.CreateProductRequest;
import br.com.projeto.demo.product.interfaces.web.dto.response.ProductDetailResponse;
import br.com.projeto.demo.product.interfaces.web.mappers.ProductWebMapper;
import br.com.projeto.demo.product.interfaces.web.swagger.ProductControllerDocs;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Driving Adapter HTTP: recebe a requisição, valida, delega ao Use Case e retorna o status HTTP.
 * NÃO contém regra de negócio. Anotações OpenAPI ficam na interface ProductControllerDocs.
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductControllerDocs {

    private final CreateProductUseCase createProductUseCase;
    private final ProductWebMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase, ProductWebMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductDetailResponse> create(@Valid @RequestBody CreateProductRequest request) {
        var product = createProductUseCase.execute(request.name(), request.sku(), request.price());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(product));
    }
}
