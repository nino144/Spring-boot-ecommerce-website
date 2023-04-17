package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Color;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Override
    @Query("SELECT p FROM Color p WHERE p.active = true")
    public List<Color> findAll();

    @Query("SELECT i FROM Color i")
    public Page<Color> findAllColor(Pageable pageable);

    @Query("SELECT COUNT(i) > 0 FROM Color i WHERE i.color = :colorName")
    public boolean existsByColorName(@Param("colorName") String colorName);

    @Query("SELECT i FROM Color i WHERE i.color = :color")
    public Color findColorByName(@Param("color") String colorName);
}
