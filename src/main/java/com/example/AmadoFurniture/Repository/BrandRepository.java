package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Override
    @Query("SELECT p FROM Brand p WHERE p.active = true")
    public List<Brand> findAll();

    @Query("SELECT i FROM Brand i")
    public Page<Brand> findAllBrand(Pageable pageable);

    @Query("SELECT COUNT(i) > 0 FROM Brand i WHERE i.brand_name = :brandName")
    public boolean existsByBrandName(@Param("brandName") String brandName);

    @Query("SELECT i FROM Brand i WHERE i.brand_name = :brandName")
    public Brand findBrandByName(@Param("brandName") String brandName);
    
}
