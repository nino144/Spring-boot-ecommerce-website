package com.example.AmadoFurniture.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AmadoFurniture.Service.OrderService;
import com.example.AmadoFurniture.Service.Pagination;
import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.model.Users;
import com.example.AmadoFurniture.model.UserOrder;
import com.example.AmadoFurniture.model.OrderDetail;

@Controller
public class OrderController {

    @Autowired
    private UsersService UsersService;

    @Autowired
    private OrderService OrderService;

    public Users user;
    public Page<UserOrder> orders;
    public Page<OrderDetail> details;
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order")
    public String getOrder(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                           @RequestParam(name = "sortField", defaultValue = "order_id") String sortField,
                           @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                           @RequestParam(name="date-sort", required=false) String dateSort,
                           Model model){   
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
                    
        user = UsersService.getUserByEmail(email);
        if(dateSort != null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = dateFormat.parse(dateSort);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                orders = OrderService.findOrderByDate(currentPage, 2, sortField, sortDir, sqlDate ,user);   
            } catch (ParseException e) {
                e.printStackTrace();
            }
              
        }
        else{
            orders = OrderService.findAllOrderByUser(currentPage, 2, sortField, sortDir, user);
        }
        
        //details = OrderService.findAllOrderDetailByOrder(currentPage, 1, sortField, sortDir, order);

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
        
        return "order";
    }
   
    @GetMapping("/order-detail/{id}")
    public String getOrderDetail(@PathVariable("id") int orderId,
                                 @RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                                 @RequestParam(name = "sortField", defaultValue = "orderitem_id") String sortField,
                                 @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                                 @RequestParam(name="name-sort", required=false) String nameSort,
                                  Model model){
        
        if(nameSort != null){
            details = OrderService.findOrderDetailByName(currentPage, 2, sortField, sortDir, nameSort);
        }
        else{
            details = OrderService.findAllDetailByOrder(currentPage, 2, sortField, sortDir, orderId);
        }
                                    
        int totalPages = details.getTotalPages();
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
        model.addAttribute("details", details);
        return "order-detail"; 
    }
}

