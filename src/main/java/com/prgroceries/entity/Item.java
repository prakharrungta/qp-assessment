package com.prgroceries.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private Integer id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private Integer quantity = 0;
	
//	@OneToMany(mappedBy = "item")
//	private Set<OrderedItem> itemOrders;
	
	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2, RoundingMode.DOWN);
	}
	
//	public void setQuantity(Integer quantity) {
//		if(quantity < 0) {
//			throw new RuntimeException("Not enough inventory to place this order");
//		}
//	}
}
