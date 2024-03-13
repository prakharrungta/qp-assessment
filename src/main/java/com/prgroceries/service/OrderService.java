package com.prgroceries.service;

import java.util.List;

import com.prgroceries.entity.Item;

public interface OrderService {
	public void makeOrder(List<Item> newItem);
}
