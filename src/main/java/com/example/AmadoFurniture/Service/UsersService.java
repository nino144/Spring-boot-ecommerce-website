package com.example.AmadoFurniture.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.AmadoFurniture.model.*;

import jakarta.mail.MessagingException;

public interface UsersService {
    public Users getUser(int id);
    public Users getUserByEmail(String email);
    public void  saveUser(Users user);
    public Page<Users> findAllUsers(int currentPage, int size, String sortField, 
                                              String sortDirection);
    public Page<Users> findUserByName(String name, int currentPage,int size,
    String sortField, String sortDirection);   
    public void updateUserByState(int userId, boolean state);
    public void updateUserRole(int userId, List<Role> roles);
    public boolean isAccountExist(String email);
    public void register(Users user, String siteURL) throws UnsupportedEncodingException, MessagingException;
    public void sendVerificationEmail(Users user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    public boolean verify(String verificationCode, List<Role> roles);
                                      
}
