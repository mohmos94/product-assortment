package no.furniture.productassortment.service;

import no.furniture.productassortment.model.Product;
import no.furniture.productassortment.repository.DatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final DatabaseRepository databaseRepository;

    public ProductService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }


    public Product getProductByID(int productID){
        return databaseRepository.getProduct(productID);
    }



}
