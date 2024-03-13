package com.prgroceries.controller;

import java.util.ArrayList;
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
import com.prgroceries.entity.ItemOrder;
import com.prgroceries.service.ItemServiceImpl;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RequestMapping
@RestController
public class MyController {
	
	@Autowired
	ItemServiceImpl itemService;
	
	@GetMapping("item")
	public List<Item> getItems() {
		return itemService.getItems();
	}
	
	@PostMapping("item")
	public void addItemsInInventory(@RequestBody List<Item> items) {
		itemService.addItems(items);
	}
	
	@DeleteMapping("item")
	public void removeItemFromInventory(@RequestBody Item itemToBeDeleted) {
		itemService.deleteItem(itemToBeDeleted);
	}
	
	@PutMapping("item")
	public void updateItemInfo(@RequestBody Item itemToBeUpdated) {
		itemService.updateItemInfo(itemToBeUpdated);
	}
	
	@PostMapping("order")
	public String makeOrder(ItemOrder order) {
		return "";
	}
}
