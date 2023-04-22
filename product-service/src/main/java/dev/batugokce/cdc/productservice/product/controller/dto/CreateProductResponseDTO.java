package dev.batugokce.cdc.productservice.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductResponseDTO {
    private long id;
    private String name;
    private int quantity;
}
