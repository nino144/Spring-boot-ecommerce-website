package com.example.AmadoFurniture.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AmadoFurniture.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public List<Role> findByName(String name);
}