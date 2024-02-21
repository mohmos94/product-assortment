package no.furniture.productassortment.mapper;


import no.furniture.productassortment.model.Customer;
import no.furniture.productassortment.model.Order;
import no.furniture.productassortment.model.Product;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RowMapper {

    public static Order orderMappingToObject(ResultSet rs, int i) throws SQLException {
        try {
            return new Order(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("product_id"),
                    rs.getDate("order_date").toLocalDate(),
                    rs.getInt("quantity"),
                    rs.getDouble("discount"));
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Order from ResultSet: " + e.getMessage());
        }
    }

    public static Product productMappingToObject(ResultSet rs, int i) throws SQLException {
        return new Product(
                rs.getInt("id"),
                rs.getString("category"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("description"));
    }


    public static Customer customerMappingToObject(ResultSet rs, int i) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBoolean("is_member"));
    }

}
