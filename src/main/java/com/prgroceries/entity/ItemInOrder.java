package com.prgroceries.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ItemInOrder represents a unique item in an order. The field orderQty represents the qty of that unique item in this particular order. 
 * If a user selects 5 items in an order, there will be 5 instances of ItemInOrder generated which is tied to that order.
 * This entity class is the JOIN TABLE of the 2 entities, Item and Order, that have a many-to-many relation with each other.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "order_items")
public class ItemInOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID itemOrderId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	private Integer orderedQty;
	
	private BigDecimal sellingPrice;
	
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice.setScale(2, RoundingMode.DOWN);
	}
}
