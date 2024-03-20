package com.prgroceries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.prgroceries.entity.Item;

public interface ItemService {
	public List<Item> getItems();
	public String addItems(List<Item> newItems);
	public String deleteItem(Item itemToBeDeleted);
	public Item updateItemInfo(Item updatedItem);
}
