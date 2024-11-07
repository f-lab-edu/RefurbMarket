package com.refurbmarket.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.refurbmarket.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponseDto {
	private Long categoryId;
	private String name;
	private List<CategoryResponseDto> children;

	public static CategoryResponseDto of(Category category) {
		return new CategoryResponseDto(category.getId(), category.getName(), new ArrayList<>());
	}
}
