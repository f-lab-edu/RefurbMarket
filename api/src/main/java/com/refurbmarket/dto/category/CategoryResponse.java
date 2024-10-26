package com.refurbmarket.dto.category;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryResponse {
	private Long categoryId;
	private int depth;
	private Long parentId;
	private String name;
	private List<CategoryResponse> children;
}
