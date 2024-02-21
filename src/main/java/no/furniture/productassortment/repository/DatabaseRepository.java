package no.furniture.productassortment.repository;

import no.furniture.productassortment.mapper.RowMapper;
import no.furniture.productassortment.model.Customer;
import no.furniture.productassortment.model.CustomerOrder;
import no.furniture.productassortment.model.Order;
import no.furniture.productassortment.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class DatabaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM order_table WHERE order_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{orderId}, RowMapper::orderMappingToObject);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Could not fetch data with following ID: " + orderId, ex);
        }
    }


    public String createOrder(int customerID, int productID, double price, Product product, CustomerOrder customerOrder) throws Exception {
        String sqlCreate = "INSERT INTO order_table (customer_id, product_id, order_date, category, name, quantity, price, discount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ? , ?)";
        try {
            jdbcTemplate.update(sqlCreate,
                    customerID,
                    productID,
                    LocalDate.now(),
                    product.category(),
                    product.name(),
                    customerOrder.quantity(),
                    price,
                    customerOrder.discount());

            return "order created";

        } catch (DataAccessException ex) {
            throw new RuntimeException("Could not create order: " + ex);
        }
    }

    public Product getProduct(int productID) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productID}, RowMapper::productMappingToObject);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Could not fetch data: " + ex);
        }
    }

    public Customer getCustomer(int customerID) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{customerID}, RowMapper::customerMappingToObject);

        } catch (DataAccessException ex) {
            throw new RuntimeException("Could not fetch data: " + ex);
        }
    }

    public List<Order> getAllOrder() {

        String sql = "SELECT * FROM order_table";
        try {
            return jdbcTemplate.query(sql, RowMapper::orderMappingToObject);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Could not fetch data: " + ex);
        }
    }
}
