package com.refurbmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Furniture {
	private Long id;
	private Long sellerId;
	private Long categoryId;
	private String name;
	private String imageUrl;
	private Long price;
	private int stock;
	private int deliveryFee;
}
