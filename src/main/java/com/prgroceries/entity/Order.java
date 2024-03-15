package com.prgroceries.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderedItem> itemOrders;
	
	private BigDecimal amount = BigDecimal.ZERO;
	
	private Date creationTime;
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(2, RoundingMode.DOWN);
	}
}
