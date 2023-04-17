package com.example.AmadoFurniture.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "color")
@Data
public class Color {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int color_id; 
    private String color;
    private boolean active;

    
    
}
