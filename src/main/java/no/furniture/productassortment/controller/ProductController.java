package no.furniture.productassortment.controller;


import no.furniture.productassortment.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{productID}")
    public ResponseEntity getOrder(
            @PathVariable int productID){
        return ResponseEntity.ok(productService.getProductByID(productID));
    }

}
