package com.refurbmarket.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FurnitureResponseDto {
	private Long furnitureId;
	private String name;
	private String imageUrl;
	private int price;
	private int salePrice;
	private int discountRate;
	private Long sellerId;
	private String salesCompanyName;
	private List<OptionGroupResponseDto> optionGroupList;
}
