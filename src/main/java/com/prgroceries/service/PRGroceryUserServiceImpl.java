package com.prgroceries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prgroceries.config.BadInputException;
import com.prgroceries.entity.PRGroceryUser;
import com.prgroceries.repository.PRGroceryUserRepo;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class PRGroceryUserServiceImpl implements UserDetailsService, PRGroceryUserService{

	@Autowired
	PRGroceryUserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepo.findByUsername(username);
		return user;
	}

	@Override
	public String createUser(PRGroceryUser user) {
		if(user.getUsername() == null)
			throw new BadInputException("Must enter a username!");
		if(userRepo.existsById(user.getUsername()))
			throw new BadInputException("Username already exists! Please enter a different username.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		String outputMsg = "Successfully created user " + user.getUsername();
		log.info(outputMsg);
		return outputMsg;
	}
}
