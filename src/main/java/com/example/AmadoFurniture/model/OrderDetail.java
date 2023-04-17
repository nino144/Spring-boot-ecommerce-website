package com.example.AmadoFurniture.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderdetail")
@Data
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderitem_id;
    private String item_name;
    private String item_image;
    private double price;
    private int quantity;
    private double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private UserOrder order;

    public OrderDetail(){}

    public OrderDetail(String item_name, String item_image, double price, int quantity, double total, UserOrder order){
        this.item_name = item_name;
        this.item_image = item_image;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.order = order;
    }
}
