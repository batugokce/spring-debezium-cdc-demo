package dev.batugokce.cdc.productservice.product.entity;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@UtilityClass
public class ProductFactory {

    private static final Random RANDOM = new Random();

    private static final List<String> PRODUCT_NAMES = Arrays.asList(
            "Pencil",
            "Chair",
            "Television",
            "Phone",
            "Book",
            "Laptop",
            "Desk",
            "Tablet",
            "Headphones",
            "Camera",
            "Keyboard",
            "Mouse",
            "Monitor",
            "Speaker",
            "Smartwatch",
            "Printer",
            "Scanner",
            "Projector",
            "Microphone",
            "Router"
    );

    public static Product generateRandomProduct() {
        return Product.builder()
                .name(PRODUCT_NAMES.get(RANDOM.nextInt(PRODUCT_NAMES.size())))
                .price(BigDecimal.ONE.add(BigDecimal.valueOf(RANDOM.nextDouble()).multiply(BigDecimal.valueOf(1000L).subtract(BigDecimal.ONE))).setScale(2, RoundingMode.HALF_UP))
                .quantityLeft(RANDOM.nextInt(1000))
                .build();
    }
}
