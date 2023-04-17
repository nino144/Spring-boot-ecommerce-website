package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Users;
import java.util.List;
import com.example.AmadoFurniture.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {
    
    @Query("SELECT i FROM ShippingAddress i WHERE i.users = :users")
    public List<ShippingAddress> findShippingAddressByUser(@Param("users") Users users);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             

}

