package no.furniture.productassortment.service;

import no.furniture.productassortment.model.CustomProductOrder;
import no.furniture.productassortment.model.Customer;
import no.furniture.productassortment.model.Order;
import no.furniture.productassortment.model.Product;
import no.furniture.productassortment.repository.DatabaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private DatabaseRepository databaseRepository;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(databaseRepository);
    }

    @Test
    public void testGetOrderById() {
        int orderId = 1;
        Order order = new Order(orderId, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);
        Customer customer = new Customer(1, "mosti", true);
        Product product = new Product(1, "Furniture", "bed", 100, "nicee looking bed");
        CustomProductOrder customProductOrder = new CustomProductOrder(
                customer.id(), customer.name(), customer.isMember(), product.category(),
                product.name(), product.description(), order.quantity(), product.price(), order.discount());

        when(databaseRepository.getOrderById(orderId)).thenReturn(order);
        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);
        when(databaseRepository.getProduct(orderId)).thenReturn(product);

        CustomProductOrder resultOrder = orderService.getOrderById(orderId);

        assertEquals(customProductOrder, resultOrder);
    }

}
