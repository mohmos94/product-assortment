package no.furniture.productassortment.model;

public record Discount(
        String customerName,
        String Category,
        String productName,

        double fullPrice,
        double percentage,
        double discountedPrice) {
}
