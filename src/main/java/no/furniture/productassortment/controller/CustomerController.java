package no.furniture.productassortment.controller;


import no.furniture.productassortment.model.Customer;
import no.furniture.productassortment.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customerID}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable int customerID){
        return ResponseEntity.ok(customerService.getOrderById(customerID));
    }



}
