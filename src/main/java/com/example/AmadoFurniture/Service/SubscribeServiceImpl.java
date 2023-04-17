package com.example.AmadoFurniture.Service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSubscribeMail(String email) throws UnsupportedEncodingException, MessagingException {
        String senderName = "Furnitute Shop Amado";
        String subject = "Subscribe letter";
        String content = "You have been subsribe to Amado ,<br>"
                + "Hope you will happy when shopping at Amado";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("nhuthienlang@gmail.com", senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);
        
        mailSender.send(message);
    }
    
}
