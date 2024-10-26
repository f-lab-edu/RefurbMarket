package com.refurbmarket.dto.furniture;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FurnitureSearchByKeywordRequestDto {
	private String keyword;
	private Integer pageSize = 10;
	private Integer pageNumber = 0;
}
