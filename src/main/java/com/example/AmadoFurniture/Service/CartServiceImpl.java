package com.example.AmadoFurniture.Service;

import com.example.AmadoFurniture.Repository.CartItemRepository;
import com.example.AmadoFurniture.Repository.CartRepository;
import com.example.AmadoFurniture.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository CartRepository;
    
    @Autowired
    private CartItemRepository CartItemRepository;

    @Override
    public Cart getCart(int id) {
        Optional<Cart> CartOptional = CartRepository.findById(id);
        if (CartOptional.isPresent()) {
            return CartOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }
    
    @Override
    public Cart findCartByUser(Users users) {
        return (Cart) CartRepository.findCartByUser(users);
    }

    @Override
    public Page<CartItem>findCartItemsByCart(Cart cart,Pageable pageable) {
        updateCartTotal(cart);
        return CartItemRepository.findCartItemsByCart(cart,pageable);
    }

    @Override
    public List<CartItem>findCartItemsByCart(Cart cart) {
        updateCartTotal(cart);
        return CartItemRepository.findCartItemsByCart(cart);
    }
    
    @Override
    public CartItem getCartItemByProduct(Product product) {
        return (CartItem) CartItemRepository.findCartItemsByProduct(product);
    }

    @Override
    public void deleteCartItem(int id,Cart cart) {
        CartItemRepository.deleteById(id);
        updateCartTotal(cart);
    }
    
    @Override
    public void saveCartItem(CartItem item) {
        CartItemRepository.save(item);
        updateCartTotal(item.getCart());
    }
    
    @Override
    public void updateCartTotal(Cart cart) {
       List<CartItem> items = CartItemRepository.findCartItemsByCart(cart);
       double totalPrice = 0;
       for(CartItem item : items) {
           totalPrice += item.getQuantity() * item.product.getPrice();
       }
       cart.setTotal_price(totalPrice);
       CartRepository.save(cart);
    }

    @Override
    public void addNewOrUpdateItem(int quantity, Cart cart, Product product) {
        CartItem item = CartItemRepository.findCartItemByCartAndProduct(cart,product);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            saveCartItem(item);
        } 
        else{
            saveCartItem(new CartItem(quantity,cart,product));  
        }
    }
    
    @Override
    public CartItem getCartItemById(int id) {
        Optional<CartItem> itemOptional = CartItemRepository.findById(id);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void deleteAllCartItem(List<CartItem> items) {
        CartItemRepository.deleteAll(items);
    }
    
}
