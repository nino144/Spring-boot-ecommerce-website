package com.example.AmadoFurniture.form;

import com.example.AmadoFurniture.model.Category;
import com.example.AmadoFurniture.model.Image;
import java.util.List;
import lombok.Data;

@Data
public class ProductDetailForm {
    private int product_id;
    private String product_name;
    private String main_image;
    private double price;
    private int max_quantity;
    private String description;
    private String category_name;
    private List<Image> images;
//in model class it is category class,but we just need the name so construstor take the category name only
    
    public ProductDetailForm(){}
    
    public ProductDetailForm(int product_id, String product_name, String main_image, double price, int quantity, String description, Category category,List<Image> images) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.main_image = main_image;
        this.price = price;
        this.max_quantity = quantity;
        this.description = description;
        this.category_name = category.getCategory_name();
        this.images = images;
    }
}
