package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Image;
import com.example.AmadoFurniture.model.Product;
import java.util.List;

public interface ImageService {
    public List<Image> findImagesByProduct(Product product);
    public void saveAllImage(List<Image> images);
}
