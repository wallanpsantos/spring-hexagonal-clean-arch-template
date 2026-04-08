package br.com.projeto.demo.product.interfaces.web.advice;

import br.com.projeto.demo.product.domain.exceptions.ProductAlreadyDiscontinuedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Driving Adapter de erros: converte exceções do domínio em respostas HTTP.
 * Usa RFC 9457 Problem Details (Spring 6+).
 * O domínio NUNCA conhece códigos HTTP — essa conversão é responsabilidade desta camada.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductAlreadyDiscontinuedException.class)
    public ProblemDetail handleProductAlreadyDiscontinued(ProductAlreadyDiscontinuedException ex) {
        log.warn("Violação de regra de negócio: {}", ex.getMessage());
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problem.setTitle("Produto já descontinuado");
        return problem;
    }
}
