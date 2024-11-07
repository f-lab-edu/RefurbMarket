package com.refurbmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.refurbmarket.dto.response.FurnitureResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FurnitureControllerIntegrationTest {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@DisplayName("전체/카테고리별 가구 조회 성공")
	@Test
	public void searchByCategory() throws Exception {
		// given
		final String url = "/furniture/search/category";
		final FurnitureSearchByCategoryRequestDto request = furnitureSearchByCategoryRequestDto();
		// when
		final ResponseEntity<FurnitureListResponseDto> response = testRestTemplate.postForEntity(
			url,
			request,
			FurnitureListResponseDto.class);
		// then
		final FurnitureListResponseDto result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getList()).hasSize(request.getPageSize());
	}

	@DisplayName("검색어 기반 가구 조회 성공")
	@Test
	public void searchByKeyword() throws Exception {
		// given
		final String url = "/furniture/search/keyword";
		// when
		final ResponseEntity<FurnitureListResponseDto> response = testRestTemplate.postForEntity(
			url,
			furnitureSearchByKeywordRequestDto(),
			FurnitureListResponseDto.class);
		// then
		final FurnitureListResponseDto result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getList())
			.allMatch(item -> item.getName().contains("침대 프레임"));
	}

	@DisplayName("특정 가구 상세 정보 조회 성공")
	@Test
	public void getFurniture() throws Exception {
		// given
		final Long furnitureId = 1L;
		final String url = "/furniture/{id}";
		// when
		ResponseEntity<FurnitureResponseDto> response = testRestTemplate.getForEntity(url, FurnitureResponseDto.class,
			furnitureId);
		// then
		final FurnitureResponseDto result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result).isNotNull();
		assertThat(result.getFurnitureId()).isEqualTo(furnitureId);
	}

	private FurnitureSearchByKeywordRequestDto furnitureSearchByKeywordRequestDto() {
		return new FurnitureSearchByKeywordRequestDto("침대 프레임", 2, 0);
	}

	private FurnitureSearchByCategoryRequestDto furnitureSearchByCategoryRequestDto() {
		return new FurnitureSearchByCategoryRequestDto(1L, 2, 0);
	}

}
