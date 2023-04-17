package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.ShippingAddress;
import com.example.AmadoFurniture.model.Users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.AmadoFurniture.Repository.ShippingAddressRepository;

public class ShippingAddressServiceImpl implements ShippingAddressService {
    
    @Autowired
    private ShippingAddressRepository ShippingAddressRepository;
    
    @Override
    public List<String> findShippingAddressByUser(Users user){
        List<ShippingAddress> listAddress = ShippingAddressRepository.findShippingAddressByUser(user);
        List<String> listAddressToString = new ArrayList<String>();

        for (ShippingAddress address : listAddress) {
            listAddressToString.add(address.toString());
        }
        
        return listAddressToString;
    }
}
