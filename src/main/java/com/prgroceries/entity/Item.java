package com.prgroceries.entity;

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
	private Double price;
	
	private Integer quantity = 0;
	
	@OneToMany(mappedBy = "item")
	private Set<ItemOrder> itemOrders;
}
