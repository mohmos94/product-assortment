package no.furniture.productassortment.repository;

import no.furniture.productassortment.mapper.FurnitureRowMapper;
import no.furniture.productassortment.model.items.Furniture;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FurnitureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FurnitureRowMapper furnitureRowMapper;

    public FurnitureRepository(NamedParameterJdbcTemplate jdbcTemplate, FurnitureRowMapper furnitureRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.furnitureRowMapper = furnitureRowMapper;
    }

    public List<Furniture> getAllFurniture() {
        String sql = "SELECT * FROM furniture";
        return jdbcTemplate.query(sql, furnitureRowMapper::furnitureMappingToObject);
    }
}
