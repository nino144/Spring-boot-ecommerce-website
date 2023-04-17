package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Cart;
import com.example.AmadoFurniture.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    @Query("SELECT i FROM Cart i WHERE i.users = :users")
    public Cart findCartByUser(@Param("users") Users users);

}
