package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.AmadoFurniture.Repository.RoleRepository;

public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleRepository RoleRepository;
    
    @Override
    public List<Role> findRoleByName(String name){
        return RoleRepository.findByName(name);
    }
}
