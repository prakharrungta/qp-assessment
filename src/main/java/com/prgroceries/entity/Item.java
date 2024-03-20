package com.prgroceries.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prgroceries.config.BadInputException;
import com.prgroceries.config.InventoryInsufficientException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer itemId;
	
	@Column(nullable = false, length = 100, unique = true)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private Integer quantity = 0;
	
	@JsonIgnore
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private Set<ItemInOrder> itemInOrders;
	
	public void setPrice(BigDecimal price) {
		if(price.compareTo(BigDecimal.ZERO) == -1) {
			throw new BadInputException("Price cannot be less than zero!");
		}
		this.price = price.setScale(2, RoundingMode.DOWN);
	}
	
	public void setQuantity(Integer quantity) {
		if(quantity < 0) {
			throw new InventoryInsufficientException("Operation halted. Inventory count cannot be less than zero!");
		}
		this.quantity = quantity;
	}
	
}
