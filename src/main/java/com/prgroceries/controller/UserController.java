package com.prgroceries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgroceries.entity.PRGroceryUser;
import com.prgroceries.service.PRGroceryUserService;

@RequestMapping("users")
@RestController
public class UserController {

	@Autowired
	PRGroceryUserService userService;
	
	@PostMapping
	public String createUser(@RequestBody PRGroceryUser user) {
		return userService.createUser(user);
	}
}
