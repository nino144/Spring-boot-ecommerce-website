package com.example.AmadoFurniture.form;

import lombok.Data;

@Data
public class CheckOutForm {
    public String user_name;
    public String email;
    public String address;
    public String gender;
    public Long phone_number;
    public String order_description;
    public String payment;
    public double total_price;
    
    public CheckOutForm(){}
    
    public CheckOutForm(String user_name, String email, String gender, Long phone_number, double total_price) {
        this.user_name = user_name;
        this.email = email;
        this.gender = gender;
        this.phone_number = phone_number;
        this.total_price = total_price;
    }
    
}
