package com.example.AmadoFurniture.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cartitem")
@Data
public class CartItem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cartitem_id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    public Product product;
    
    public CartItem(){}
    
    public CartItem(int quantity,Cart cart,Product product){
        this.quantity = quantity;
        this.cart = cart;
        this.product = product;
    }
    
    
}
