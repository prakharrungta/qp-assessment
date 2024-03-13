package com.prgroceries.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.prgroceries.entity.Item;

public interface ItemService {
	public List<Item> getItems();
	public void addItems(List<Item> newItems);
	public void deleteItem(Item itemToBeDeleted);
	public void updateItemInfo(Item updatedItem);
	public void updateItemInventory(int itemId, int changeInQty);
	public void updateItemInventory(Item itemWithUpdatedQty);
}
