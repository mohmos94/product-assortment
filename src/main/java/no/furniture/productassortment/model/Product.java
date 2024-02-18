package no.furniture.productassortment.model;

public record Product(
        int id,
        int price,
        String description,
        Category category
) {
}
