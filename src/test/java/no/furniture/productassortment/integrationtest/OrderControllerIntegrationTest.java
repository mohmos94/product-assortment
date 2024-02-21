package no.furniture.productassortment.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.furniture.productassortment.model.*;
import no.furniture.productassortment.repository.DatabaseRepository;
import no.furniture.productassortment.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @MockBean
    private DatabaseRepository databaseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnListOfOrders() throws Exception {
        Order order = new Order(1, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);

        when(databaseRepository.getAllOrder()).thenReturn(List.of(order));

        mockMvc.perform(MockMvcRequestBuilders.get("/Order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        List<Order> orderList = databaseRepository.getAllOrder();

        assertThat(orderList).contains(order);
    }

    @Test
    void shouldReturnOrderByID() throws Exception {
        int orderId = 1;
        Order order = new Order(orderId, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);
        Customer customer = new Customer(1, "mosti", true);
        Product product = new Product(1, "Furniture", "bed", 100, "nicee looking bed");

        when(databaseRepository.getOrderById(orderId)).thenReturn(order);
        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);
        when(databaseRepository.getProduct(orderId)).thenReturn(product);

        CustomProductOrder customProductOrder = new CustomProductOrder(
                customer.id(), customer.name(), customer.isMember(), product.category(),
                product.name(), product.description(), order.quantity(), product.price(), order.discount());


        mockMvc.perform(MockMvcRequestBuilders.get("/Order/{orderID}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        CustomProductOrder mockCustomProductOrder = orderService.getOrderById(orderId);

        assertThat(customProductOrder).isEqualTo(mockCustomProductOrder);
    }

    @Test
    void shouldCreateOrder() throws Exception {

        int orderId = 1;
        Order order = new Order(orderId, 1, 1, LocalDate.of(2024, 1, 1), 10, 10);
        Customer customer = new Customer(1, "mosti", true);
        Product product = new Product(1, "Furniture", "bed", 100, "nicee looking bed");

        CustomerOrder customerOrder = new CustomerOrder(1, 10);

        when(databaseRepository.getOrderById(orderId)).thenReturn(order);
        when(databaseRepository.getCustomer(orderId)).thenReturn(customer);
        when(databaseRepository.getProduct(orderId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/Order/{customerID}/{productID}", customer.id(), product.productID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerOrder)))
                .andExpect(status().isCreated());

        String mockResponse = orderService.createOrder(order.customerID(), order.productID(), customerOrder);

        assertThat("created Order").isEqualTo(mockResponse);

    }

}
