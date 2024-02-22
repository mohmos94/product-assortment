package no.furniture.productassortment.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Date;


public record Order(

        @Schema(name = "Order iD", description = "The order id.",example = "1", required = true)
        int orderID,
        @Schema(name = "Customer ID", description = "The ID of an existing customer.",example = "1", required = true)
        int customerID,
        @Schema(name = "Order ID", description = "The ID of an existing customer.",example = "1", required = true)
        int productID,
        @Schema(name = "Order date.", description = "The date of purchasing the order.",example = "1", required = true)
        LocalDate localDate,
        @Schema(name = "Number of product to buy.", description = "Nunmber of product to buy.",example = "1", required = true)
        int quantity,
        @Schema(name = "Discount percentage.", description = "Write the percentage of discount.",example = "20", required = true)
        double discount
) {


}