package com.example.AmadoFurniture.form;

import lombok.Data;

@Data
public class CartForm {
    public int cartitem_id;
    public String product_name;
    public int quantity;
    public int max_quantity;
    public String main_image;
    public double price;
    public double total;
    
    
    public CartForm(){}

    public CartForm(int cartitem_id, String product_name, int quantity,int max_quantity, String main_image, double price, double total) {
        this.cartitem_id = cartitem_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.max_quantity = max_quantity;
        this.main_image = main_image;
        this.price = price;
        this.total = total;
    }
    
    
}
