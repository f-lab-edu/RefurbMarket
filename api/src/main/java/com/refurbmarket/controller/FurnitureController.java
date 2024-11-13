package com.refurbmarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.response.FurnitureResponseDto;
import com.refurbmarket.service.FurnitureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Furniture", description = "Furniture API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/furniture")
public class FurnitureController {
	private final FurnitureService furnitureService;

	@Operation(summary = "전체/카테고리별 가구 조회", description = "마켓의 전체/카테고리에 해당하는 가구를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "전체/카테고리별 가구 조회 성공"
	)
	@GetMapping("/search/category")
	public List<FurnitureResponseDto> searchByCategory(
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "100") int limit,
		@RequestParam(required = false) Long categoryId) {
		return furnitureService.getFurnitureListByCategory(page, limit, categoryId);
	}

	@Operation(summary = "검색어 기반 가구 조회", description = "검색어에 해당하는 가구를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "검색어 기반 가구 조회 성공"
	)
	@GetMapping("/search/keyword")
	public List<FurnitureResponseDto> searchByKeyword(
		@RequestParam(required = false, defaultValue = "1") int page,
		@RequestParam(required = false, defaultValue = "100") int limit,
		@RequestParam(required = false, defaultValue = "") String keyword) {
		return furnitureService.getFurnitureListByKeyword(page, limit, keyword);
	}

	@Operation(summary = "특정 가구 상세 정보 조회", description = "특정 가구의 상세 정보를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "특정 가구 상세 정보 조회 성공"
	)
	@GetMapping("/{id}")
	public FurnitureResponseDto getFurniture(@PathVariable Long id) {
		return furnitureService.getFurnitureById(id);
	}
}
