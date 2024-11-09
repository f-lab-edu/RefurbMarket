package com.refurbmarket.dto.request;

import lombok.Getter;

@Getter
public class OrderItemRequestDto {
	private Long furnitureId;
	private int quantity;
}
