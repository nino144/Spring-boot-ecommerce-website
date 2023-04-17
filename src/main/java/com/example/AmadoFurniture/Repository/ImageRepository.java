package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Image;
import com.example.AmadoFurniture.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    
    @Query("SELECT i FROM Image i WHERE i.product = :product")
    public List<Image> findImagesByProduct(@Param("product") Product product);
}
