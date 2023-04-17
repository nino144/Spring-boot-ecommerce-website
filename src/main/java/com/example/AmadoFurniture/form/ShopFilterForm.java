package com.example.AmadoFurniture.form;

import java.util.List;
import lombok.Data;

@Data
public class ShopFilterForm {
    public List<String> brands;
    public String color;
    public String min_price;
    public String max_price;
}
