package com.refurbmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
	private Long id;
	private Long orderId;
	private Long furnitureId;
	private int quantity;
}
