package com.prgroceries.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgroceries.entity.Item;
import com.prgroceries.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{

}
