package no.furniture.productassortment.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import no.furniture.productassortment.exception.SimpleErrorResponse;
import no.furniture.productassortment.model.CustomProductOrder;
import no.furniture.productassortment.model.CustomerOrder;
import no.furniture.productassortment.model.Order;
import no.furniture.productassortment.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
@Tag(name = "Order Controller", description = "Manage orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create an order",
            description = "Create a new order from an existing customer and the product in the store."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully.",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Failed to create order. Check customerID or productID.",
                    content = @Content(schema = @Schema(implementation = SimpleErrorResponse.class)))
    })
    @PostMapping("{customerID}/{productID}")
    public ResponseEntity<String> createOrder(
            @PathVariable int customerID,
            @PathVariable int productID,
            @RequestBody CustomerOrder customerOrder
    ) {
        String order = orderService.createOrder(customerID, productID, customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Operation(
            summary = "Get all orders",
            description = "Retrieve all existing orders."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved orders.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error. Please check the database connection.",
                    content = @Content(schema = @Schema(implementation = SimpleErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(
            summary = "Get a specific order",
            description = "Retrieve a specific order by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched order.",
                    content = @Content(schema = @Schema(implementation = CustomProductOrder.class))),
            @ApiResponse(responseCode = "400", description = "Failed to find order.",
                    content = @Content(schema = @Schema(implementation = SimpleErrorResponse.class)))
    })
    @GetMapping("/{orderID}")
    public ResponseEntity<CustomProductOrder> getOrder(
            @PathVariable int orderID) {
        return ResponseEntity.ok(orderService.getOrderById(orderID));
    }
}
