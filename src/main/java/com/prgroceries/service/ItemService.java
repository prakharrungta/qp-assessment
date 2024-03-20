package com.prgroceries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.prgroceries.entity.Item;

public interface ItemService {
	public List<Item> getAllItems();
	public List<Item> getAvailableItems();
	public String addItems(List<Item> newItems);
	public String deleteItem(Integer itemId);
	public Item updateItemInfo(Item updatedItem);
}
