package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {}
