package com.prgroceries.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * OrderedItem represents a unique item in an order. The field orderQty represents the qty of that unique item in this particular order. 
 * If a user selects 5 items in an order, there will be 5 instances of OrderedItem generated which is tied to that order.
 * This entity class is the JOIN TABLE of the 2 entities, Item and Order, that have a many-to-many relation with each other.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "orders_items")
public class OrderedItem {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Item item;
	
	private Integer orderedQty;
	
	private BigDecimal sellingPrice;
	
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice.setScale(2, RoundingMode.DOWN);
	}
}
