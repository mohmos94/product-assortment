package no.furniture.productassortment.model;

public record CustomProductOrder(

        int customerID,
        String customerName,
        boolean memberShip,
        String category,
        String productName,
        String description,
        int quantity,

        int price,
        double discount

) {

}
