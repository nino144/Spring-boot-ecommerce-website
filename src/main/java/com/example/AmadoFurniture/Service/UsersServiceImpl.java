package com.example.AmadoFurniture.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.AmadoFurniture.Repository.UsersRepository;
import com.example.AmadoFurniture.model.Role;
import com.example.AmadoFurniture.model.Users;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.UUID;

public class UsersServiceImpl implements UsersService {
    
    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public Users getUser(int id) {
        Optional<Users> usersOptional = UsersRepository.findById(id);

        if (usersOptional.isPresent()) {
            return usersOptional.get();
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void saveUser(Users user) {
        UsersRepository.save(user);
    }

    @Override
    public Page<Users> findAllUsers(int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
        Sort.by(sortField).ascending() :Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(currentPage, size, sort);
        return UsersRepository.findAllUsers(pageable);
    }

    @Override
    public Page<Users> findUserByName(String name, int currentPage, int size, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(sortField).ascending() :Sort.by(sortField).descending();
        
        Pageable pageable = PageRequest.of(currentPage, size, sort);
        
        return UsersRepository.findUserByName(name, pageable);
    }

    @Override
    public void updateUserByState(int userId, boolean state) {
        Optional<Users> optionalUser = UsersRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setEnabled(state);
            UsersRepository.save(user);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public void updateUserRole(int userId, List<Role> roles) {
        Optional<Users> optionalUser = UsersRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setRoles(roles);
            UsersRepository.save(user);
        }
        else{
            throw new IllegalArgumentException("Not exists.");
        }
    }

    @Override
    public boolean isAccountExist(String email) {
        return UsersRepository.isAccountExist(email);
    }

    @Override
    public Users getUserByEmail(String email) {
        return UsersRepository.getUserByEmail(email);
    }

    @Override
    public void register(Users user, String siteURL) throws UnsupportedEncodingException, MessagingException{
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); 
        String randomCode = UUID.randomUUID().toString();
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        
        UsersRepository.save(user);
        
        sendVerificationEmail(user, siteURL);
    }

    @Override
    public void sendVerificationEmail(Users user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String senderName = "Furnitute Shop Amado";
        String subject = "Please verify your registration";
        String content = "Dear lovely cusotmer ,<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("nhuthienlang@gmail.com", senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        
        content = content.replace("[[name]]", user.getUser_name());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        
        content = content.replace("[[URL]]", verifyURL);
        
        helper.setText(content, true);
        
        mailSender.send(message);
    }

    public boolean verify(String verificationCode, List<Role> roles) {
        Users user = UsersRepository.findByVerificationCode(verificationCode);
         
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            user.setRoles(roles);
            UsersRepository.save(user);
            return true;
        }
         
    }
    
}
