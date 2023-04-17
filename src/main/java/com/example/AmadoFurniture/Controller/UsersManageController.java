package com.example.AmadoFurniture.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.Service.RoleService;
import com.example.AmadoFurniture.model.Users;
import com.example.AmadoFurniture.model.Role;
import com.example.AmadoFurniture.Service.Pagination;

@Controller
public class UsersManageController {

    @Autowired
    private UsersService UsersService;

    public Page<Users> users;

    @Autowired
    private RoleService RoleService;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users-manage")
    public String getUsersManage(@RequestParam(name = "page", defaultValue = "0", required = false) Integer currentPage,
                                 @RequestParam(name = "sortField", defaultValue = "user_id") String sortField,
                                 @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
                                 @RequestParam(name="name-sort", required=false) String name,
                                 @RequestParam(name="id", required=false) Integer id,
                                 @RequestParam(name="role", required=false) String role,
                                 Model model){   
        
        if(name != null) {
            users = UsersService.findUserByName(name, currentPage, 3, sortField, sortDir);
        }
        else{
            users = UsersService.findAllUsers(currentPage, 3, sortField, sortDir);                             
        }

        if(id != null) {
            List<Role> roles = RoleService.findRoleByName(role);
            UsersService.updateUserRole(id,roles);
        }
        
        int totalPages = users.getTotalPages();
        Pagination pagination = new Pagination(totalPages,currentPage);
        List<Integer> paginationResult = pagination.getPagination();
        int startPage = paginationResult.get(0);
        int endPage = paginationResult.get(1);
        int startIndex = currentPage * 3 + 1;

        model.addAttribute("startIndex", startIndex);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("users",users);
        return "users-manage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users-manage/statechange")
    public String changeBrandState(@RequestParam(name = "id") Integer userId,
                                   @RequestParam(name = "state") boolean state){
        if(userId != null){
            UsersService.updateUserByState(userId, !state);
        }
        return "redirect:/users-manage";
    }
}
