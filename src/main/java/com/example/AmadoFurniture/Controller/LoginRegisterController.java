package com.example.AmadoFurniture.Controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.Service.RoleService;
import com.example.AmadoFurniture.form.LoginRegisterForm;
import com.example.AmadoFurniture.model.Role;
import com.example.AmadoFurniture.model.Users;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginRegisterController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsersService UsersService;

    @Autowired
    private RoleService RoleService;
    
    @GetMapping("/login")
    public String getLogin(Model model){  
        
        LoginRegisterForm form = new LoginRegisterForm();
        model.addAttribute("form",form);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String getRegister(Model model){     
        LoginRegisterForm form = new LoginRegisterForm();
        model.addAttribute("form",form);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("form") LoginRegisterForm form,
                                HttpServletRequest request,Model model) 
                            throws UnsupportedEncodingException, MessagingException{       

        String email = form.getEmail();
        String password = form.getPassword();
        String repassword = form.getRepassword();

        if(UsersService.isAccountExist(email)){
            model.addAttribute("error","Email is exist!");
            return "register";
        }
        else if(!password.equals(repassword)){
            model.addAttribute("error","Password and Repassword must be the same!");
            return "register";
        }
        else if(password.equals(repassword)){      
            Users user = new Users();     
            user.setEmail(email);
            user.setPassword(password);
            user.setCreated_date(new Date());
            UsersService.register(user, getSiteURL(request));
        }
        return "redirect:/login";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        List<Role> roles = RoleService.findRoleByName("USER");
        UsersService.verify(code,roles);
        return "redirect:/login";
    }

}
