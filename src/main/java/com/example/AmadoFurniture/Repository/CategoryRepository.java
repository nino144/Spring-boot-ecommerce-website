package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Override
    @Query("SELECT p FROM Category p WHERE p.active = true")
    public List<Category> findAll();

    @Query("SELECT i FROM Category i")
    public Page<Category> findAllCategory(Pageable pageable);

    @Query("SELECT COUNT(i) > 0 FROM Category i WHERE i.category_name = :categoryName")
    public boolean existsByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT i FROM Category i WHERE i.category_name = :category")
    public Category findCategoryByName(@Param("category") String categoryName);
}
