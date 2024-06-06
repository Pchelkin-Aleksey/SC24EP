package controller;

import controller.payload.NewProductPayload;
import entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import service.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {
    private final ProductService productService;

    public List<Product> getProducts() {
        return this.productService.findAllProducts();
    }

    public ResponseEntity<Product> createProduct(@Valid @RequestBody NewProductPayload payload,
                                                 BindingResult bindingResult,
                                                 UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }// также можно указать путь к собственной странице с исключением
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/catalogue-api/products/{productId}") // вместо можно использовать .pathSegment({productId}), чтобы отталкиваться от пути контроллера
                            .build(Map.of("productId", product.getId())))
                    .body(product);
        }
    }
}
