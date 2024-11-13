package com.refurbmarket.dto.response;

import com.refurbmarket.domain.Furniture;
import com.refurbmarket.domain.Seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FurnitureResponseDto {
	private Long furnitureId;
	private String furnitureName;
	private Long sellerId;
	private String storeName;
	private String imageUrl;
	private Long price;
	private int deliveryFee;

	public static FurnitureResponseDto of(Furniture furniture, Seller seller) {
		return new FurnitureResponseDto(
			furniture.getId(),
			furniture.getName(),
			seller.getId(),
			seller.getStoreName(),
			furniture.getImageUrl(),
			furniture.getPrice(),
			furniture.getDeliveryFee());
	}
}
