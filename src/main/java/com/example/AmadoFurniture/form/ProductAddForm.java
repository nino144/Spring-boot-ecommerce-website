package com.example.AmadoFurniture.form;

import com.example.AmadoFurniture.model.Product;

import lombok.Data;

@Data
public class ProductAddForm {
    public int product_id;
    public String product_name;
    public String color;
    public String brand_name;
    public String category_name;
    public Double price;
    public int max_quantity; 
    public String description;
    public String main_image;

    public ProductAddForm(){}

    public ProductAddForm(Product product){
        this.product_name = product.getProduct_name();
        this.max_quantity = product.getMax_quantity();
        this.color = product.color.getColor();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.brand_name = product.brand.getBrand_name();
        this.category_name = product.category.getCategory_name();
    }
}
