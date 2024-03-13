package com.prgroceries.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgroceries.config.BadInputException;
import com.prgroceries.config.ResourceNotFoundException;
import com.prgroceries.entity.Item;
import com.prgroceries.repository.ItemRepository;

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
	public void addItems(List<Item> newItems) {
		List<Item> savedItems = itemRepo.saveAll(newItems);
		log.info(savedItems.size() + " new items have been added in inventory");
	}

	@Override
	public void deleteItem(Item itemToBeDeleted) {
		if(itemToBeDeleted.getId() == null)
			throw new BadInputException("Item id is missing in input!");
		
		if(!itemRepo.existsById(itemToBeDeleted.getId()))
			throw new ResourceNotFoundException(itemToBeDeleted + " does not exist in inventory!");
		
		itemRepo.deleteById(itemToBeDeleted.getId());
		log.info(itemToBeDeleted + " has been removed from inventory");
		
		
	}

	@Override
	public void updateItemInfo(Item updatedItem) {
		if(updatedItem.getId() == null)
			throw new BadInputException("Item id is missing in input!");
		
		Item itemInInv = itemRepo.findById(updatedItem.getId()).orElseThrow(() -> new ResourceNotFoundException(updatedItem + " does not exist in inventory!"));
		if(!updatedItem.getName().isBlank())
			itemInInv.setName(updatedItem.getName());
		if(updatedItem.getPrice() != null)
			itemInInv.setPrice(updatedItem.getPrice());
		if(updatedItem.getQuantity() != null)
			itemInInv.setQuantity(updatedItem.getQuantity());
		itemRepo.save(itemInInv);
		log.info(itemInInv + " info has been updated");
	}

	@Override
	public void updateItemInventory(int itemId, int changeInQty) {
		Optional<Item> itemOp = itemRepo.findById(itemId);
		if(itemOp.isPresent()) {
			Item item = itemOp.get();
			int updatedQty = item.getQuantity() + changeInQty;
			if(updatedQty > 0) {
				item.setQuantity(item.getQuantity() + changeInQty);
				itemRepo.save(item);
				log.info(item.getName() + ": updated qty = " + item.getQuantity());
			} else {
				log.error("Inventory level not enough for this operation! Existing qty is only = " + item.getQuantity());
			}
			
		} else {
			log.error("Item does not exist in inventory!");
		}
		
	}

	@Override
	public void updateItemInventory(Item itemWithUpdatedQty) {
		itemRepo.save(itemWithUpdatedQty);
		log.info(itemWithUpdatedQty.getName() + " qty has been updated");
	}

}
