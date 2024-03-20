package com.prgroceries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgroceries.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	public List<Item> findByQuantityNot(Integer quantity);
}
