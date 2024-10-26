package com.refurbmarket.dto.category;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponseDto {
	private Long categoryId;
	private int depth;
	private Long parentId;
	private String name;
	private List<CategoryResponseDto> children;
}
