package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findRoleByName(String name);
}
