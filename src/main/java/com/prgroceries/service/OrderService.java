package com.prgroceries.service;

import java.util.List;
import java.util.UUID;

import com.prgroceries.entity.Item;
import com.prgroceries.entity.Order;

public interface OrderService {
	public String makeOrder(List<Item> itemsOrdered);
	public List<Order> getOrders();
	/**
	public String deleteOrder(UUID orderId);
	public Order getOrder(UUID orderId);**/
}
