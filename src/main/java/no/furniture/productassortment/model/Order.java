package no.furniture.productassortment.model;

import java.util.List;

public record Order(
        Long id,
        List<Product> products,
        List<Discount> discounts) {
}
