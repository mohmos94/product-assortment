package no.furniture.productassortment.model;

public record Product(
        int productID,
        String category,
        String name,
        int price,
        String description) {
}
