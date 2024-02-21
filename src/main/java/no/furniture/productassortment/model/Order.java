package no.furniture.productassortment.model;

import java.time.LocalDate;
import java.util.Date;


public record Order(
        int orderID,
        int customerID,
         int productID,
         LocalDate localDate,
         int quantity,
         double discount
){



}