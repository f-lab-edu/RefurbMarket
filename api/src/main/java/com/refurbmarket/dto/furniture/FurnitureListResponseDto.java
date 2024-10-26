package com.refurbmarket.dto.furniture;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FurnitureListResponseDto {
	private List<FurnitureResponseDto> list;
	private long totalElements;
	private long totalPages;
	private int pageNumber;
	private int pageSize;
	private boolean hasPrevious;
	private boolean hasNext;
}
