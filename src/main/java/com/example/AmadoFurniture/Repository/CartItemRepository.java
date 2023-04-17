package com.example.AmadoFurniture.Repository;

import com.example.AmadoFurniture.model.Cart;
import com.example.AmadoFurniture.model.CartItem;
import com.example.AmadoFurniture.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    
    @Query("SELECT i FROM CartItem i WHERE i.cart = :cart")
    public Page<CartItem> findCartItemsByCart(@Param("cart") Cart cart,Pageable pageable);
    
    @Query("SELECT i FROM CartItem i WHERE i.cart = :cart")
    public List<CartItem> findCartItemsByCart(@Param("cart") Cart cart);
    
    @Query("SELECT i FROM CartItem i WHERE i.product = :product")
    public CartItem findCartItemsByProduct(@Param("product") Product product);
    
    @Query("SELECT i FROM CartItem i WHERE i.cart = :cart AND i.product = :product")
    public CartItem findCartItemByCartAndProduct(@Param("cart") Cart cart,
                                                 @Param("product") Product product);
}
