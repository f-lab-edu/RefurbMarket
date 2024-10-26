package com.refurbmarket.dto.furniture;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FurnitureSearchByKeywordRequestDto {
	private Long categoryId;
	private Integer pageSize = 10;
	private Integer pageNumber = 0;
}
