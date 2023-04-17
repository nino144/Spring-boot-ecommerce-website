package com.example.AmadoFurniture.form;

import com.example.AmadoFurniture.model.Users;

import lombok.Data;

@Data
public class AccountForm {
    public int user_id;
    public String user_name; 
    public String email;
    public String password;
    public String newpassword;
    public String gender;
    public String main_address;
    public long phone;
    public String main_image;

    public AccountForm(){}

    public AccountForm(Users user){
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.main_address = user.getMain_address();
        this.phone = user.getPhone();
        this.main_image = user.getMain_image();
    }
}
