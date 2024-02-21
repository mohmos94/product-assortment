package no.furniture.productassortment.service;


import no.furniture.productassortment.model.Customer;
import no.furniture.productassortment.model.Order;
import no.furniture.productassortment.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    final DatabaseRepository databaseRepository;


    public CustomerService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public Customer getOrderById(int customerID) {
        return databaseRepository.getCustomer(customerID);
    }




}
