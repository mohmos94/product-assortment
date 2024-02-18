package no.furniture.productassortment.mapper;

import no.furniture.productassortment.model.items.Furniture;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FurnitureRowMapper {

    public Furniture furnitureMappingToObject(ResultSet resultSet, int i) throws SQLException {
        return new Furniture(
                resultSet.getString("item"),
                resultSet.getString("name"),
                resultSet.getDouble("weight"));
    }
}
