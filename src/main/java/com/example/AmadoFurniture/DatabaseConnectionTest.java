package com.example.AmadoFurniture;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        //For testing only
        
       //String password = "123";
       /* String hashedPassword = passwordEncoder.encode(password);
        
        System.out.println("Original password: " + password);
        System.out.println("Hashed password: " + hashedPassword);*/

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123";
        String testPasswordEncoded = passwordEncoder.encode(password);
        System.out.println("Encoded Password ="+ testPasswordEncoded);
        boolean matched = passwordEncoder.matches("123",testPasswordEncoded);
        System.out.println(matched);
    }
} 
