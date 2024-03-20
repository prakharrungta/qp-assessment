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
@RequestMapping("item")
@RestController
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping
	public List<Item> getItems() {
		return itemService.getItems();
	}
	
	@PostMapping
	public String addItemsInInventory(@RequestBody List<Item> items) {
		return itemService.addItems(items);
	}
	
	@DeleteMapping
	public String removeItemFromInventory(@RequestBody Item itemToBeDeleted) {
		return itemService.deleteItem(itemToBeDeleted);
	}
	
	@PutMapping
	public Item updateItemInfo(@RequestBody Item itemToBeUpdated) {
		return itemService.updateItemInfo(itemToBeUpdated);
	}
	
}
