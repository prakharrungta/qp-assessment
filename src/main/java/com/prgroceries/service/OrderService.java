package com.prgroceries.service;

import java.util.List;

import com.prgroceries.entity.Item;
import com.prgroceries.entity.OrderedItem;

public interface OrderService {
	public void makeOrder(List<Item> itemsOrdered);
}
