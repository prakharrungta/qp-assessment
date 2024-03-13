package com.prgroceries.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ItemOrder represents a unique item in an order. The field orderQty represents the qty of that unique item in this particular order. 
 * If a user selects 5 items in an order, there will be 5 instances of ItemOrder generated which is tied to that order.
 * This entity class is the JOIN TABLE of the 2 entities, Item and Order, that have a many-to-many relation with each other.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "orders_items")
public class ItemOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;
	
	private Double orderedQty;
}
