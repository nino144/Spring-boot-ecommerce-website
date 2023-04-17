package com.example.AmadoFurniture.Controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AmadoFurniture.Service.SubscribeService;

import jakarta.mail.MessagingException;

@Controller
public class SubscribeController {

    @Autowired
    SubscribeService subscribeService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("email") String email) throws UnsupportedEncodingException, MessagingException {
        if(email != null){
            subscribeService.sendSubscribeMail(email);
        }
        
        return "redirect:/index";
    }
}
