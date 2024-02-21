package no.furniture.productassortment.model;

import java.util.List;

public record Customer(
        int id,
        String name,
        boolean isMember) {
}
