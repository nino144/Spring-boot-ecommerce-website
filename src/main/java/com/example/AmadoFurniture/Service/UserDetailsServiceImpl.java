package com.example.AmadoFurniture.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.AmadoFurniture.Repository.UsersRepository;
import com.example.AmadoFurniture.model.MyUserDetails;
import com.example.AmadoFurniture.model.Users;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository UsersRepository;

	
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		Users user = UsersRepository.getUserByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		else{
			return new MyUserDetails(user);			
		}
		
	}

}
