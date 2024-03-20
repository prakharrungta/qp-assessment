package com.prgroceries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgroceries.config.BadInputException;
import com.prgroceries.config.ResourceNotFoundException;
import com.prgroceries.entity.Item;
import com.prgroceries.repository.ItemRepository;

import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepo;
	
	@Override
	public List<Item> getItems() {
		return itemRepo.findAll();
	}

	@Override
	public String addItems(List<Item> newItems) {
		List<Item> savedItems = itemRepo.saveAll(newItems);
		String outputMsg = savedItems.size() + " new items have been added in inventory";
		log.info(outputMsg);
		return outputMsg;
	}

	@Override
	@Transactional
	public String deleteItem(Item itemToBeDeleted) {
		if(itemToBeDeleted.getItemId() == null)
			throw new BadInputException("Item id is missing in input!");
		
		if(!itemRepo.existsById(itemToBeDeleted.getItemId()))
			throw new ResourceNotFoundException("Item does not exist in inventory!");
		
		itemToBeDeleted = itemRepo.findById(itemToBeDeleted.getItemId()).get();
		
		//set item_id as null in the order_items table for this item.
		itemToBeDeleted.getItemInOrders().forEach((itemInOrder) -> itemInOrder.setItem(null));
		
		itemRepo.deleteById(itemToBeDeleted.getItemId());
		String outputMsg = "item has been removed from inventory";
		log.info(outputMsg);
		return outputMsg;
	}

	@Override
	@Transactional
	public Item updateItemInfo(Item updatedItem) {
		if(updatedItem.getItemId() == null)
			throw new BadInputException("Item id is missing in input!");
		
		Item itemInInv = itemRepo.findById(updatedItem.getItemId()).orElseThrow(() -> new ResourceNotFoundException(updatedItem + " does not exist in inventory!"));
		if(updatedItem.getName()!=null && !updatedItem.getName().isBlank())
			itemInInv.setName(updatedItem.getName());
		if(updatedItem.getPrice()!=null && updatedItem.getPrice() != null)
			itemInInv.setPrice(updatedItem.getPrice());
		if(updatedItem.getQuantity()!=null && updatedItem.getQuantity() != null)
			itemInInv.setQuantity(updatedItem.getQuantity());
		itemRepo.save(itemInInv);
		
		log.info(itemInInv.getName() + ": " + " info has been updated");
		return itemInInv;
	}
}
