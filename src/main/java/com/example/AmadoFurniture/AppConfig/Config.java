package com.example.AmadoFurniture.AppConfig;

import com.example.AmadoFurniture.Service.*;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {
    
    @Bean
    public ProductService getProductService(){
        return new ProductServiceImpl();
    }
    
    @Bean
    public ImageService getImageService(){
        return new ImageServiceImpl();
    }
    
    @Bean
    public CartService getCartService(){
        return new CartServiceImpl();
    }
    
    @Bean
    public CategoryService getCategoryService(){
        return new CategoryServiceImpl();
    }
    
    @Bean
    public BrandService getBrandService(){
        return new BrandServiceImpl();
    }
    
    @Bean
    public ColorService getColorService(){
        return new ColorServiceImpl();
    }
    
    @Bean
    public OrderService getOrderService(){
        return new OrderServiceImpl();
    }
    
    @Bean
    public UsersService getUsersService(){
        return new UsersServiceImpl();
    }
    
    @Bean
    public ShippingAddressService getShippingAddressService(){
        return new ShippingAddressServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public RoleService getRoleService() {
        return new RoleServiceImpl();
    }

    @Bean
    public SubscribeService getSubscribeService() {
        return new SubscribeServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("1@gmail.com"); //Your email
        mailSender.setPassword("2"); //Your password
  
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.from", "1@gmail.com");
  
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
    

}

