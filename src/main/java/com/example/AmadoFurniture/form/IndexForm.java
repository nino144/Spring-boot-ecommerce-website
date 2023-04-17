package com.example.AmadoFurniture.form;

import lombok.Data;

@Data
public class IndexForm {
    private int product_id;
    private String product_name;
    private String main_image;
    private double price;
    
    
    public IndexForm(int product_id,String product_name,String main_image,double price){
        this.product_id = product_id;
        this.product_name = product_name;
        this.main_image = main_image;
        this.price = price;
    }
}
