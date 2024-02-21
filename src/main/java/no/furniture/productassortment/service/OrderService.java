package no.furniture.productassortment.service;

import no.furniture.productassortment.model.*;
import no.furniture.productassortment.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class OrderService {

    final int percentage = 100;
    DatabaseRepository databaseRepository;

    public OrderService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public CustomProductOrder getOrderById(int orderId) {
        requireNonNull(orderId, "cannot be null");

        Order order = databaseRepository.getOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found for ID: " + orderId);
        }
        Customer customer = databaseRepository.getCustomer(order.customerID());
        if (customer == null) {
            throw new RuntimeException("Customer not found for ID: " + order.customerID());
        }
        Product product = databaseRepository.getProduct(order.productID());
        if (product == null) {
            throw new RuntimeException("Product not found for ID: " + order.productID());
        }
        CustomProductOrder customProductOrder = new CustomProductOrder(
                order.customerID(),
                customer.name(),
                customer.isMember(),
                product.category(),
                product.name(),
                product.description(), order.quantity(), product.price(), order.discount());
        return customProductOrder;
    }

    public String createOrder(int customerID, int productId, CustomerOrder customerOrder) {
        requireNonNull(customerID, "customerID be null");
        requireNonNull(productId, "productId be null");


        Product product = databaseRepository.getProduct(productId);
        if (product == null) {
            throw new RuntimeException("Product not found for ID: " + productId);
        }
        Customer customer = databaseRepository.getCustomer(customerID);
        if (customer == null) {
            throw new RuntimeException("Customer not found for ID: " + customerID);
        }
        if (!customer.isMember()) {
            throw new RuntimeException("Customer cannot get a discount is not a member in the startup. Check customer Data first.");
        }

        double discountPrice = calculateDiscountedPrice(product.price(), customerOrder.discount());

        databaseRepository.createOrder(customerID, productId, discountPrice, product, customerOrder);

        return "created Order";

    }

    public List<Order> getAllOrders() {
        return databaseRepository.getAllOrder();
    }


    private double calculateDiscountedPrice(double price, double discountPercentage) {
        final int percentage = 100;
        return price * ((percentage - discountPercentage) / percentage);
    }
}
