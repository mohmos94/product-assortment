package no.furniture.productassortment.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerOrder(
        @Schema(name = "Number of product to buy.", example = "1", required = true)
        int quantity,
        @Schema(name = "Discounted percentage.", description = "Write the percentage of discount.", example = "20", required = true)
        double discount
) {
}
