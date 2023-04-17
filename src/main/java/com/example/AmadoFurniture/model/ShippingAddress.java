package com.example.AmadoFurniture.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "shippingaddress")
@Data
public class ShippingAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String district;
    private String street;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
    
    public String toString(){//overriding the toString() method  
        return city + " " + district + " " + street;  
       } 
}
