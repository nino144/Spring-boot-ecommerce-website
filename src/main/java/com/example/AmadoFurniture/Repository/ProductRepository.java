package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Category;
import com.example.AmadoFurniture.model.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE p.brand.brand_name IN :brands AND p.price BETWEEN :minPrice AND :maxPrice AND p.color.color = :color AND p.active = true")
    public Page<Product> findProductsByShopFilter(@Param("brands") List<String> brands,
                                           @Param("minPrice") double minPrice, 
                                           @Param("maxPrice") double maxPrice, 
                                           @Param("color") String color,
                                           Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.product_name LIKE %:name% AND p.active = true")
    public Page<Product> findProductByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.product_name LIKE %:name%")
    public Page<Product> findProductByNameBothState(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category =:category AND p.active = true")
    public Page<Product> findProductByCategory(@Param("category") Category category, Pageable pageable);

    @Query("SELECT p FROM Product p")
    public Page<Product> findAllBothState(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.product_id = :id")
    public Optional<Product> findProductByIdBothState(@Param("id") int id);
    
    @Override
    @Query("SELECT p FROM Product p WHERE p.active = true")
    public Page<Product> findAll(Pageable pageable);

    @Query("SELECT COUNT(i) > 0 FROM Product i WHERE i.product_id = :productId")
    public boolean existsByProductId(@Param("productId") int productId);

    @Query("SELECT p FROM Product p WHERE p.product_id = :id AND p.active = true")
    public Optional<Product> findProductById(@Param("id") int id);
    
}