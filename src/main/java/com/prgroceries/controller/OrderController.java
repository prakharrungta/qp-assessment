package com.prgroceries.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prgroceries.entity.Item;
import com.prgroceries.entity.Order;
import com.prgroceries.service.OrderService;


@RequestMapping("order")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public String makeOrder(@RequestBody List<Item> orderedItems) {
		String outputMsg = orderService.makeOrder(orderedItems);
		return outputMsg;
	}
	
	/**
	 * Just to validate if the order is created. Will be removed once MySQL DB is setup.
	 * @return list of all orders
	 */
	@GetMapping
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
}
