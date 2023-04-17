package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.model.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {
    public Cart getCart(int id);
    public Cart findCartByUser(Users users);
    public Page<CartItem> findCartItemsByCart(Cart cart,Pageable pageable);
    public List<CartItem> findCartItemsByCart(Cart cart);
    public CartItem getCartItemByProduct(Product product);
    public void deleteCartItem(int id,Cart cart);
    public void deleteAllCartItem(List<CartItem> items);
    public void saveCartItem(CartItem item);
    public void updateCartTotal(Cart cart);
    public void addNewOrUpdateItem(int quantity,Cart cart, Product product);
    public CartItem getCartItemById(int id);
}
