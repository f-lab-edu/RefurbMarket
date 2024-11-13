package com.refurbmarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.response.CategoryResponseDto;
import com.refurbmarket.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Category", description = "Category API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	@Operation(summary = "모든 카테고리 정보 조회", description = "마켓의 모든 카테고리 정보를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "모든 카테고리 정보 조회 성공"
	)
	@GetMapping
	public List<CategoryResponseDto> getCategories() {
		return categoryService.getCategories();
	}
}
