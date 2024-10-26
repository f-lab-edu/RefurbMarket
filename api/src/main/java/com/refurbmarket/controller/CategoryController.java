package com.refurbmarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.category.CategoryResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category", description = "Category API")
@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Operation(summary = "모든 카테고리 정보 조회", description = "마켓의 모든 카테고리 정보를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "모든 카테고리 정보 조회 성공"
	)
	@GetMapping
	public List<CategoryResponseDto> getCategories() {
		return List.of(
			new CategoryResponseDto(1L, 1, null, "침대", List.of(
				new CategoryResponseDto(10L, 2, 1L, "침대프레임", List.of(
					new CategoryResponseDto(100L, 3, 10L, "일반침대", null),
					new CategoryResponseDto(101L, 3, 10L, "수납침대", null))))),
			new CategoryResponseDto(2L, 1, null, "테이블", List.of(
				new CategoryResponseDto(20L, 2, 2L, "식탁", List.of(
					new CategoryResponseDto(200L, 3, 20L, "입식테이블", null),
					new CategoryResponseDto(201L, 3, 20L, "아일랜드식탁", null))))));
	}
}
