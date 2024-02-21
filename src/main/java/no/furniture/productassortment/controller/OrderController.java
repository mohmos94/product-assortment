package no.furniture.productassortment.controller;


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
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("{customerID}/{product_ID}")
    public ResponseEntity<String> createOrder(
            @PathVariable int customerID,
            @PathVariable int product_ID,
            @RequestBody CustomerOrder customerOrder
    ){

        String order = orderService.createOrder(customerID, product_ID, customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("{orderID}")
    public ResponseEntity<CustomProductOrder> getOrder(
            @PathVariable int orderID){
        return ResponseEntity.ok(orderService.getOrderById(orderID));
    }
}
