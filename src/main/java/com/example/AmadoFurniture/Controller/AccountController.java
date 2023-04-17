package com.example.AmadoFurniture.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.AmadoFurniture.Service.UsersService;
import com.example.AmadoFurniture.form.AccountForm;
import com.example.AmadoFurniture.model.Users;

@Controller
public class AccountController {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private UsersService UsersService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Users user;
    
    @PreAuthorize("isAuthenticated()")  
    @GetMapping("/account")
    public String getAccount(Model model){ 

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        user = UsersService.getUserByEmail(email);
        
        AccountForm form = new AccountForm(user);
        model.addAttribute("form",form);
        return "account";
    }

    @PostMapping("/account")
    public String postAccount(@ModelAttribute("form") AccountForm account,
                              @RequestParam("files") MultipartFile file,
                              Model model){
        String password = account.getPassword();
        String newpassword = account.getNewpassword();
        Long phone = account.getPhone();
        String user_name = account.getUser_name();
        String gender = account.getGender(); 
        String address = account.getMain_address();

        if(user_name != null){
            user.setUser_name(user_name);
        }

        if(gender != null){
            user.setGender(gender);
        }

        if(phone != null){
            user.setPhone(phone);
        }

        if(address != null){
            user.setMain_address(address);
        }

        if(password != null && newpassword != null){
            if (passwordEncoder.matches(password,user.getPassword())){
                user.setPassword(passwordEncoder.encode(newpassword));
            }
        }

        if(file != null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            Path srcPath = Paths.get("src");
            Path mainPath = Paths.get("main");
            Path resourcePath = Paths.get("resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("image");
            Path filePath = CURRENT_FOLDER.resolve(srcPath).resolve(mainPath).resolve(resourcePath)
                                          .resolve(staticPath).resolve(imagePath).resolve(fileName);
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(file.getBytes());
            } catch (IOException ioe) {  
                ioe.printStackTrace();     
            } 

            user.setMain_image(fileName);
        }

        UsersService.saveUser(user);
        
        return "redirect:/account";
    }
   
}
