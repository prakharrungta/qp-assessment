package com.prgroceries.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgroceries.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
