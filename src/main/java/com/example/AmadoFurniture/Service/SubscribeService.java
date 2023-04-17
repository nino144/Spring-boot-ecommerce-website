package com.example.AmadoFurniture.Service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface SubscribeService {
    public void sendSubscribeMail(String email) throws UnsupportedEncodingException, MessagingException;
}
