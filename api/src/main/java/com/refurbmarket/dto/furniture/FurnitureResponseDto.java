package com.refurbmarket.dto.furniture;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FurnitureResponseDto {
	private Long productId;
	private String name;
	private String imageUrl;
	private int price;
	private int salePrice;
	private int discountRate;
	private Long sellerId;
	private String salesCompanyName;
	private List<OptionGroupResponseDto> optionGroupList;
}
