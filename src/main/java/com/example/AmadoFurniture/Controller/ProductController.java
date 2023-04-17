package com.example.AmadoFurniture.Controller;

import com.example.AmadoFurniture.Service.*;
import com.example.AmadoFurniture.form.ProductDetailForm;
import com.example.AmadoFurniture.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService ProductService;
    
    @Autowired
    private ImageService ImageService;
    
    @Autowired
    private UsersService UsersService;
    
    @Autowired
    private CartService CartService;
    
    ProductDetailForm form;
    
    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable("id") int id, Model model){   
        
        Product product = ProductService.getProductById(id);
        List<Image> images = ImageService.findImagesByProduct(product);
        
        form = new ProductDetailForm(
            product.getProduct_id(),
            product.getProduct_name(),
            product.getMain_image(),
            product.getPrice(),
            product.getMax_quantity(),
            product.getDescription(),
            product.getCategory(),
            images
        );
        
        model.addAttribute("form",form);
        return "product";
    }
    
    @PreAuthorize("isAuthenticated()")  
    @PostMapping("/product/{id}")
    public String addProductToCart(@PathVariable("id") int id, Model model,
                                   @RequestParam(name="quantity") Integer quantity){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Users user = UsersService.getUserByEmail(email);
        Cart cart = CartService.findCartByUser(user);
        Product product = ProductService.getProductById(id);
        
        CartService.addNewOrUpdateItem(quantity,cart,product);
      
        model.addAttribute("form",form);
        return "product";
    } 
}
