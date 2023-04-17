package com.example.AmadoFurniture.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AmadoFurniture.Service.OrderService;
import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.model.UserOrder;
import com.example.AmadoFurniture.model.Users;

@Controller
public class OrderManagementController {
    
    @Autowired
    private OrderService OrderService;

    @Autowired
    private UsersService UsersService;

    public Page<UserOrder> orders;
    public Users user;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/order-management")
    public String getLogin(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                           @RequestParam(name = "sortField", defaultValue = "order_id") String sortField,
                           @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                           @RequestParam(name = "user-id", required=false) Integer userId,
                           Model model){ 
        
        if(userId != null) {
            user = UsersService.getUser(userId);
            orders = OrderService.findAllOrderByUser(currentPage, 2, sortField, sortDir, user);
        }
        else{
            orders = OrderService.findAllOrder(currentPage, 2, sortField, sortDir);
        }

        int totalPages = orders.getTotalPages();
        Pagination pagination = new Pagination(totalPages,currentPage);
        List<Integer> paginationResult = pagination.getPagination();
        int startPage = paginationResult.get(0);
        int endPage = paginationResult.get(1);
        int startIndex = currentPage * 2 + 1;

        model.addAttribute("startIndex", startIndex);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("orders",orders);
                        
        return "order-management";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/order-management/transport")
    public String changeBrandState(@RequestParam(name = "id") Integer orderId){
        if(orderId != null){
            OrderService.updateOrderStatus(orderId);
        }
        return "redirect:/order-management";
    }
}
