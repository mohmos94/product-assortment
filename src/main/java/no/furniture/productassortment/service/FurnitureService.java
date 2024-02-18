package no.furniture.productassortment.service;


import no.furniture.productassortment.model.items.Furniture;
import no.furniture.productassortment.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.getAllFurniture();
    }
}
