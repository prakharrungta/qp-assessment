package com.prgroceries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.prgroceries.entity.PRGroceryUser;
import com.prgroceries.repository.PRGroceryUserRepo;

import jakarta.annotation.PostConstruct;

@Component
public class InitSetupService {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	PRGroceryUserRepo userRepo;
	
	@PostConstruct
	public void setupInitialUsers() {
		if(!userRepo.existsById("user0")) {
			PRGroceryUser user0 = new PRGroceryUser("user0", passwordEncoder.encode("pass"), "USER");
			userRepo.save(user0);
		}
		if(!userRepo.existsById("admin0")) {
			PRGroceryUser admin0 = new PRGroceryUser("admin0", passwordEncoder.encode("pass"), "ADMIN");
		 	userRepo.save(admin0);
		}
	}
}
