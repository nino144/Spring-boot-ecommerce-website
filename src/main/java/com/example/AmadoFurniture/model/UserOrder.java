package com.example.AmadoFurniture.model;

import jakarta.persistence.*;
import java.util.Date;

import com.example.AmadoFurniture.form.CheckOutForm;

import lombok.Data;

@Entity
@Table(name = "userorder")
@Data
public class UserOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    public String user_name;
    public String email;
    public String address;
    public String gender;
    public Long phone_number;
    private double total_amount;
    private String status;
    private Date created_date;
    public String order_description;
    public String payment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user__id", nullable = false)
    private Users users;

    public UserOrder(){}

    public UserOrder(CheckOutForm order_infomation, Date created_date, double total_price, Users users){
        this.user_name = order_infomation.getUser_name();
        this.email = order_infomation.getEmail();
        this.address = order_infomation.getAddress();
        this.gender = order_infomation.getGender();
        this.phone_number = order_infomation.getPhone_number();
        this.total_amount = total_price;
        this.status = "Checking";
        this.order_description = order_infomation.getOrder_description();
        this.payment = order_infomation.getPayment();
        this.created_date  = created_date;
        this.users = users;
    }
}
