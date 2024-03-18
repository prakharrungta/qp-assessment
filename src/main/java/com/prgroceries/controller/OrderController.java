package com.prgroceries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgroceries.entity.Item;
import com.prgroceries.service.ItemService;
import com.prgroceries.service.OrderService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RequestMapping("order")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public String makeOrder(@RequestBody List<Item> orderedItems) {
		orderService.makeOrder(orderedItems);
		return "order placed";
	}
}
