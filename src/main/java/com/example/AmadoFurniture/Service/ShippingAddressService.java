package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.Users;
import java.util.List;

public interface ShippingAddressService {
    public List<String> findShippingAddressByUser(Users user);
}
