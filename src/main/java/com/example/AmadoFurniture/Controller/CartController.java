package com.example.AmadoFurniture.Controller;

import com.example.AmadoFurniture.Service.CartService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.form.CartForm;
import com.example.AmadoFurniture.model.Cart;
import com.example.AmadoFurniture.model.CartItem;
import com.example.AmadoFurniture.model.Users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    
    @Autowired
    private CartService CartService;

    @Autowired
    private UsersService UsersService;
    
    @GetMapping("/cart")
    public String getCart(@RequestParam(name="page", defaultValue = "0", required=false) Integer currentPage, 
                          @RequestParam(name="remove-item", required=false) Integer item_id,
                          @RequestParam(name="item-id", required=false) Integer update_item_id,
                          @RequestParam(name="quantity", required=false) Integer quantity,
                          Model model){

        //update cart item
        if (update_item_id != null && quantity != null){
            CartItem itemToUpdate = CartService.getCartItemById(update_item_id);
            itemToUpdate.setQuantity(quantity);
            CartService.saveCartItem(itemToUpdate);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Users user = UsersService.getUserByEmail(email);
        
        Cart cart = CartService.findCartByUser(user);
        
        //delete cart item
        if (item_id != null){
            CartService.deleteCartItem(item_id,cart);
        }
        
        model.addAttribute("cartTotal",cart.getTotal_price());
        
        //make pageable with current page = page,page size = 5
        Pageable pageable = PageRequest.of(currentPage, 5);
        //get list of cart item by cart,using pageable
        Page<CartItem> items = CartService.findCartItemsByCart(cart, pageable);
        
        //pagination calculate
        //5 link
        int totalPages = items.getTotalPages();
        Pagination pagination = new Pagination(totalPages,currentPage);
        List<Integer> paginationResult = pagination.getPagination();
        int startPage = paginationResult.get(0);
        int endPage = paginationResult.get(1);
        int startIndex = currentPage * 5 + 1;

        model.addAttribute("startIndex", startIndex);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("currentPage", currentPage);
       
       
        
        List<CartForm> form = new ArrayList<>();
        
        for(CartItem item : items) {
            form.add(new CartForm(
               item.getCartitem_id(),
               item.product.getProduct_name(),
               item.getQuantity(),
               item.product.getMax_quantity(),
               item.product.getMain_image(),
               item.product.getPrice(),
               item.getQuantity()*item.product.getPrice()
            ));
        }
        
        model.addAttribute("items",form);
        return "cart";
    }
}
