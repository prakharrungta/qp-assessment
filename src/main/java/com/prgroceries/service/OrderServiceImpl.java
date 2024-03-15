package com.prgroceries.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgroceries.config.InsufficientInventoryException;
import com.prgroceries.entity.Item;
import com.prgroceries.entity.Order;
import com.prgroceries.entity.OrderedItem;
import com.prgroceries.repository.ItemRepository;
import com.prgroceries.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ItemRepository itemRepo;

	@Override
	@Transactional
	public void makeOrder(List<Item> itemsOrdered) {
		Order order = new Order();
		order.setCreationTime(new Date());
		Set<OrderedItem> orderedItems = new HashSet<>(itemsOrdered.size());
		itemsOrdered.forEach(
				(item) -> {
					int orderedQty = item.getQuantity();
					Item itemInInventory = itemRepo.getReferenceById(item.getId());
					
					if(orderedQty > itemInInventory.getQuantity())
						throw new InsufficientInventoryException("Insufficient inventory for item: " + itemInInventory.getName() + ". Existing units = " + itemInInventory.getQuantity() + ". Order request rejected.");
					
					OrderedItem orderedItem = new OrderedItem();
					orderedItem.setOrder(order);
					orderedItem.setItem(item);
					orderedItem.setOrderedQty(orderedQty);
					orderedItem.setSellingPrice(itemInInventory.getPrice());
					orderedItems.add(orderedItem);
					
					itemInInventory.setQuantity(itemInInventory.getQuantity() - orderedQty);
					itemRepo.save(itemInInventory);
					
					order.setAmount(order.getAmount().add(itemInInventory.getPrice().multiply(new BigDecimal(orderedQty))));
				});
		order.setItemOrders(orderedItems);
		orderRepo.save(order);
		log.info("Order placed for value = " + order.getAmount());
	}

}
