package com.refurbmarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.request.FurnitureSearchByCategoryRequestDto;
import com.refurbmarket.dto.request.FurnitureSearchByKeywordRequestDto;
import com.refurbmarket.dto.response.FurnitureListResponseDto;
import com.refurbmarket.dto.response.FurnitureResponseDto;
import com.refurbmarket.dto.response.OptionGroupResponseDto;
import com.refurbmarket.dto.response.OptionResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Furniture", description = "Furniture API")
@RestController
@RequestMapping("/furniture")
public class FurnitureController {
	@Operation(summary = "전체/카테고리별 가구 조회", description = "마켓의 전체/카테고리에 해당하는 가구를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "전체/카테고리별 가구 조회 성공"
	)
	@PostMapping("/search/category")
	public FurnitureListResponseDto searchByCategory(@RequestBody final FurnitureSearchByCategoryRequestDto request) {
		return new FurnitureListResponseDto(List.of(
			new FurnitureResponseDto(1L, "수납 서랍 침대", "", 1000000, 900000, 10, 1L, "먼데이하우스", List.of(
				new OptionGroupResponseDto("사이즈", List.of(
					new OptionResponseDto("SS", 0),
					new OptionResponseDto("Q", 100000))),
				new OptionGroupResponseDto("색상", List.of(
					new OptionResponseDto("핑크", 0),
					new OptionResponseDto("화이트", 0)))
			)),
			new FurnitureResponseDto(2L, "호텔식 침대 프레임", "", 1000000, 900000, 10, 2L, "오트밀하우스", List.of(
				new OptionGroupResponseDto("사이즈", List.of(
					new OptionResponseDto("SS", 0),
					new OptionResponseDto("Q", 100000))),
				new OptionGroupResponseDto("색상", List.of(
					new OptionResponseDto("블랙", 0),
					new OptionResponseDto("화이트", 0)))
			))
		), 10, 5, 1, 2, false, true);
	}

	@Operation(summary = "검색어 기반 가구 조회", description = "검색어에 해당하는 가구를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "전체/카테고리 별 가구 조회 성공"
	)
	@PostMapping("/search/keyword")
	public FurnitureListResponseDto searchByKeyword(@RequestBody final FurnitureSearchByKeywordRequestDto request) {
		return new FurnitureListResponseDto(List.of(
			new FurnitureResponseDto(2L, "호텔식 침대 프레임", "", 1000000, 900000, 10, 2L, "오트밀하우스", List.of(
				new OptionGroupResponseDto("사이즈", List.of(
					new OptionResponseDto("SS", 0),
					new OptionResponseDto("Q", 100000))),
				new OptionGroupResponseDto("색상", List.of(
					new OptionResponseDto("블랙", 0),
					new OptionResponseDto("화이트", 0)))
			)),
			new FurnitureResponseDto(3L, "원목 침대 프레임", "", 1000000, 900000, 10, 2L, "오트밀하우스", List.of(
				new OptionGroupResponseDto("사이즈", List.of(
					new OptionResponseDto("SS", 0),
					new OptionResponseDto("Q", 100000))),
				new OptionGroupResponseDto("색상", List.of(
					new OptionResponseDto("핑크", 0),
					new OptionResponseDto("블랙", 0)))
			))
		), 10, 5, 1, 2, false, true);
	}

	@Operation(summary = "특정 가구 상세 정보 조회", description = "특정 가구의 상세 정보를 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "특정 가구 상세 정보 조회 성공"
	)
	@GetMapping("/{id}")
	public FurnitureResponseDto getFurniture(@PathVariable Long id) {
		return new FurnitureResponseDto(1L, "수납 서랍 침대", "", 1000000, 900000, 10, 1L, "먼데이하우스", List.of(
			new OptionGroupResponseDto("사이즈", List.of(
				new OptionResponseDto("SS", 0),
				new OptionResponseDto("Q", 100000))),
			new OptionGroupResponseDto("색상", List.of(
				new OptionResponseDto("핑크", 0),
				new OptionResponseDto("화이트", 0)))
		));
	}
}
