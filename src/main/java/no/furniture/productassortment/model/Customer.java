package no.furniture.productassortment.model;

import java.util.List;

public record Customer(
        Long id,
        boolean isMember,
        List<Category> discountCategories) {
}
