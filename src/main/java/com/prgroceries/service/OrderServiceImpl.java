package com.prgroceries.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prgroceries.config.InventoryInsufficientException;
import com.prgroceries.config.ResourceNotFoundException;
import com.prgroceries.entity.Item;
import com.prgroceries.entity.Order;
import com.prgroceries.entity.ItemInOrder;
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
	public String makeOrder(List<Item> itemsOrdered) {
		Order order = new Order();
		order.setCreationTime(new Date());
		Set<ItemInOrder> orderedItems = new HashSet<>(itemsOrdered.size());
		itemsOrdered.forEach(
			(item) -> {
				int orderedQty = item.getQuantity();
				Item itemInInventory = itemRepo.getReferenceById(item.getItemId());
			
				if(orderedQty > itemInInventory.getQuantity())
					throw new InventoryInsufficientException("Insufficient inventory for item: " + itemInInventory.getName() + ". Existing units = " + itemInInventory.getQuantity() + ". Order request rejected.");
				
				ItemInOrder orderedItem = new ItemInOrder();
				orderedItem.setOrder(order);
				orderedItem.setItem(item);
				orderedItem.setOrderedQty(orderedQty);
				orderedItem.setSellingPrice(itemInInventory.getPrice());
				orderedItems.add(orderedItem);
					
				itemInInventory.setQuantity(itemInInventory.getQuantity() - orderedQty);
				itemRepo.save(itemInInventory);
				
				order.setAmount(order.getAmount().add(itemInInventory.getPrice().multiply(new BigDecimal(orderedQty))));
			}
		);
		order.setItemOrders(orderedItems);
		orderRepo.save(order);
		String outputMsg = "Order placed for value = " + order.getAmount();
		log.info(outputMsg);
		return outputMsg;
	}

	@Override
	public String deleteOrder(UUID orderId) {
		
		if(!orderRepo.existsById(orderId))
			throw new ResourceNotFoundException("Order does not exist!");
		
		orderRepo.deleteById(orderId);
		String outputMsg = "Order has been cancelled";
		log.info(outputMsg);
		return outputMsg;
	}

	@Override
	public List<Order> getOrders() {
		List<Order> orders = orderRepo.findAll();
		return orders;
	}

	@Override
	public Order getOrder(UUID orderId) {
		Order order = orderRepo.findById(orderId).get();
		log.info(order.toString() + " fetched from DB");
		return order;
	}

}
