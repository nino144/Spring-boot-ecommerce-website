package com.example.AmadoFurniture.Controller;

import com.example.AmadoFurniture.Service.*;
import com.example.AmadoFurniture.form.CheckOutForm;
import com.example.AmadoFurniture.model.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckOutController {
    
    @Autowired
    private CartService CartService;
    
    @Autowired
    private ShippingAddressService ShippingAddressService;

    @Autowired
    private UsersService UsersService;

    @Autowired
    private OrderService OrderService;
   
    public Users user;
    public Cart cart;
    public List<String> listAddress;
    public CheckOutForm form;
    
    @PreAuthorize("isAuthenticated()")  
    @GetMapping("/checkout")
    public String getCheckout(Model model){     
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        user = UsersService.getUserByEmail(email);
        cart = CartService.findCartByUser(user);
        listAddress = ShippingAddressService.findShippingAddressByUser(user);
        
        form = new CheckOutForm(
            user.getUser_name(),
            user.getEmail(),
            user.getGender(),
            user.getPhone(),
            cart.getTotal_price()
        );

        model.addAttribute("form", form);
        model.addAttribute("listAddress",listAddress);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String getCheckOutResult(@ModelAttribute("form") CheckOutForm formResult,
                                        Model model){       
        
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        //LocalDateTime now = LocalDateTime.now(); 

        UserOrder order = new UserOrder(formResult, new Date(), cart.getTotal_price(), user);
        OrderService.saveOrder(order);
        List<CartItem> items = CartService.findCartItemsByCart(cart);
        List<OrderDetail> details = new ArrayList<OrderDetail>();
        
        for (CartItem item : items) {
            details.add(new OrderDetail(
                item.getProduct().getProduct_name(),
                item.getProduct().getMain_image(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                item.getProduct().getPrice() * item.getQuantity(),
                order
            )); 
        }

        OrderService.saveOrderDetail(details);
        CartService.deleteAllCartItem(items);

        return "redirect:/checkout";
    }
}
