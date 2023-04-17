package com.example.AmadoFurniture.Controller;

import com.example.AmadoFurniture.Service.*;
import com.example.AmadoFurniture.form.ShopFilterForm;
import com.example.AmadoFurniture.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShopController {
    
    @Autowired
    private BrandService BrandService;
        
    @Autowired
    private CategoryService CategoryService;
    
    @Autowired
    private ColorService ColorService;
    
    @Autowired
    private ProductService ProductService;
    
    @Autowired
    private CartService CartService;
    
    @Autowired
    private UsersService UsersService;
    
    public List<Brand> brands;
    public List<Category> categories;
    public List<Color> colors;
    public Page<Product> products;
    public Product product;
    
    @GetMapping("/shop")
    public String getShop(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                          @ModelAttribute("form") ShopFilterForm formResult,
                          @RequestParam(name = "search", required = false) String search,
                          @RequestParam(name = "sortField", defaultValue = "price") String sortField,
                          @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                          @RequestParam(name="product-name", required=false) String name,
                          @RequestParam(name="cateId", required=false) Integer categoryId,
                          ShopFilterForm form,
                          Model model){ 
        
        brands = BrandService.getAllBrand();
        categories = CategoryService.getAllCategory();
        colors = ColorService.getAllColor();
                
        if(search != null){
            double min_price = Double.parseDouble(formResult.getMin_price());
            double max_price = Double.parseDouble(formResult.getMax_price());
            products = ProductService.findProductsByShopFilter(
                form.getBrands(), min_price, max_price, form.getColor(), PageRequest.of(currentPage,2));
        }
        else if(name != null){
            products = ProductService.findProductByName(name, currentPage, 3, sortField, sortDir);
        }
        else if(categoryId != null){
            Category category = CategoryService.findCategoryById(categoryId);
            products = ProductService.findProductByCategory(currentPage, 6, sortField, sortDir, category);
        }
        else{
            products = ProductService.getAllProduct(currentPage, 6, sortField, sortDir);
        }
        
        int totalPages = products.getTotalPages();
        Pagination pagination = new Pagination(totalPages,currentPage);
        List<Integer> paginationResult = pagination.getPagination();
        int startPage = paginationResult.get(0);
        int endPage = paginationResult.get(1);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        form = new ShopFilterForm();

        model.addAttribute("colors",colors);
        model.addAttribute("form",form);
        model.addAttribute("brands",brands);
        model.addAttribute("categories",categories);
        model.addAttribute("products",products);
        return "shop";
    }

    @PreAuthorize("isAuthenticated()") 
    @PostMapping("/shop")
    public String buyProduct(@RequestParam(name = "product-id", required = false) Integer product_id){
        if(product_id != null){
            Product product = ProductService.getProductById(product_id);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String email = authentication.getName();

            Users user = UsersService.getUserByEmail(email);
            Cart cart = CartService.findCartByUser(user);
            CartService.addNewOrUpdateItem(1,cart,product);
        }
        
        return "redirect:/shop";
    }
    

}
