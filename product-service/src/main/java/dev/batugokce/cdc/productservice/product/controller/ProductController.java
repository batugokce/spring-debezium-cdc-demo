package dev.batugokce.cdc.productservice.product.controller;

import dev.batugokce.cdc.productservice.product.controller.dto.CreateProductResponseDTO;
import dev.batugokce.cdc.productservice.product.entity.ProductFactory;
import dev.batugokce.cdc.productservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @PostMapping
    public CreateProductResponseDTO createNewProduct() {
        var product = ProductFactory.generateRandomProduct();
        repository.save(product);
        return new CreateProductResponseDTO(product.getId(), product.getName(), product.getQuantityLeft());
    }

    @PostMapping("/decrement-quantity/{id}")
    public void decrementQuantity(@PathVariable Long id) {
        var product = repository.findById(id).orElseThrow();
        product.decrementQuantityByOne();
        repository.save(product);
    }

}
