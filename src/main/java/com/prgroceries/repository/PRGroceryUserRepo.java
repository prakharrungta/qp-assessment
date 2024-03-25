package com.prgroceries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prgroceries.entity.PRGroceryUser;

@Repository
public interface PRGroceryUserRepo extends JpaRepository<PRGroceryUser, String>{
	
	public PRGroceryUser findByUsername(String username);
}
