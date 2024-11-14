package com.refurbmarket.dto.request;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvailableCouponsRequestDto {
	private Long userId;
	private List<OrderItemRequestDto> orderItemList;

	public Map<Long, Integer> getFurnitureQuantityMap() {
		return orderItemList.stream()
			.collect(toMap(OrderItemRequestDto::getFurnitureId, OrderItemRequestDto::getQuantity));
	}
}
