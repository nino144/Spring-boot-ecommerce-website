package com.example.AmadoFurniture.Controller;

import com.example.AmadoFurniture.Service.ProductService;
import com.example.AmadoFurniture.form.IndexForm;
import com.example.AmadoFurniture.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @Autowired
    private ProductService ProductService;
   
    
    @GetMapping("/index")
    public String getIndex(Model model){     
       
        List<Product> products = ProductService.getIndexProduct();
        List<IndexForm> form = new ArrayList<>();
         
        for(Product product : products) {
            form.add(new IndexForm(
                    product.getProduct_id(),
                    product.getProduct_name(),
                    product.getMain_image(),
                    product.getPrice()
            ));
        }
        
        model.addAttribute("products",form);
        return "index";
    }
}
