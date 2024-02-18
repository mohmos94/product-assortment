package no.furniture.productassortment.controller;


import no.furniture.productassortment.model.items.Furniture;
import no.furniture.productassortment.service.FurnitureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public List<Furniture> getAllFurniture() {
        return furnitureService.getAllFurniture();
    }

}
