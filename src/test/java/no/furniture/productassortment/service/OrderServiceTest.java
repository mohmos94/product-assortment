package no.furniture.productassortment.service;

import no.furniture.productassortment.model.*;
import no.furniture.productassortment.repository.DatabaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private DatabaseRepository databaseRepository;

    @InjectMocks
    private OrderService orderService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(databaseRepository);
    }

    @Test
     void testGetOrderByIdShouldReturnCustomProdcutOrder() {
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

    @Test
     void getOrderByIdShouldReturnRuntimeExceptionIFOrderNotFound() {
        int orderId = 100;
        assertThatThrownBy(() -> {
            orderService.getOrderById(orderId);
        }).isInstanceOf(RuntimeException.class).hasMessage("Order not found for ID: " + orderId);

    }

    @Test
     void getOrderByIdShouldReturnRuntimExceptionIfCustomerNotFoundWithFollowingOrder() {
        int orderId = 1;
        Order order = new Order(orderId, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);

        when(databaseRepository.getOrderById(orderId)).thenReturn(order);


        assertThatThrownBy(() -> {
            orderService.getOrderById(orderId);
        }).isInstanceOf(RuntimeException.class).hasMessage("Customer not found for ID: " + order.customerID());
    }

    @Test
     void getOrderByIdShouldReturnRuntimExceptionIfProductNotFoundWithFollowingOrder() {
        int orderId = 1;
        Order order = new Order(orderId, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);
        Customer customer = new Customer(1, "mosti", true);


        when(databaseRepository.getOrderById(orderId)).thenReturn(order);
        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);


        assertThatThrownBy(() -> {
            orderService.getOrderById(orderId);
        }).isInstanceOf(RuntimeException.class).hasMessage("Product not found for ID: " + order.productID());
    }


    @Test
    void createOrderShouldReturnCreatedOrderIfOrderIsSuccessful() throws Exception {
        int orderId = 1;
        String orderCreated = "created Order";
        Customer customer = new Customer(1, "mosti", true);
        Product product = new Product(1, "Furniture", "bed", 100, "nicee looking bed");
        CustomerOrder customerOrder = new CustomerOrder(2, 10);

        double discountedPrice = calculateDiscountedPrice(product.price(), customerOrder.discount());
        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);
        when(databaseRepository.getProduct(orderId)).thenReturn(product);
        when(databaseRepository.createOrder(customer.id(), product.productID(), discountedPrice, product, customerOrder)).thenReturn(orderCreated);

        String createOrder = orderService.createOrder(customer.id(), product.productID(), customerOrder);
        assertEquals(orderCreated, createOrder);

    }

    @Test
    void createOrderShouldFailIFCustomerIsNotMember()  {
        int orderId = 1;
        Customer customer = new Customer(1, "mosti", false);
        Product product = new Product(1, "Furniture", "bed", 100, "nicee looking bed");
        CustomerOrder customerOrder = new CustomerOrder(2, 10);

        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);
        when(databaseRepository.getProduct(orderId)).thenReturn(product);

        assertThatThrownBy(() -> {
            orderService.createOrder(customer.id(), product.productID(), customerOrder);
        }).isInstanceOf(RuntimeException.class).hasMessage("Customer cannot get a discount is not a member in the startup. Check customer Data first.");
    }


    private double calculateDiscountedPrice(double price, double discountPercentage) {
        final int percentage = 100;
        return price * ((percentage - discountPercentage) / percentage);
    }

}
