package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.ImageRepository;
import com.example.AmadoFurniture.model.Image;
import com.example.AmadoFurniture.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository ImageRepository;
    
    @Override
    public List<Image> findImagesByProduct(Product product) {
        return (List<Image>) ImageRepository.findImagesByProduct(product);
    }

    @Override
    public void saveAllImage(List<Image> images) {
        ImageRepository.saveAll(images);
    }
    
}
