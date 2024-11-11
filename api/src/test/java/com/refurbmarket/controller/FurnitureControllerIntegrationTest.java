package com.refurbmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.refurbmarket.dto.response.FurnitureResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FurnitureControllerIntegrationTest {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@DisplayName("전체/카테고리별 가구 조회 성공")
	@ParameterizedTest
	@MethodSource("provideSearchByCategoryParams")
	public void searchByCategory(String page, String limit, String categoryId) throws Exception {
		// given
		final URI url = UriComponentsBuilder.fromUriString("/furniture/search/category")
			.queryParam("page", page)
			.queryParam("limit", limit)
			.queryParam("categoryId", categoryId)
			.build()
			.toUri();
		// when
		ResponseEntity<List<FurnitureResponseDto>> response = testRestTemplate.exchange(
			url,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<>() {
			}
		);
		// then
		final List<FurnitureResponseDto> result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result).isNotNull();
	}

	@DisplayName("검색어 기반 가구 조회 성공")
	@ParameterizedTest
	@MethodSource("provideSearchByKeywordParams")
	public void searchByKeyword(String page, String limit, String keyword) throws Exception {
		// given
		final URI url = UriComponentsBuilder.fromUriString("/furniture/search/keyword")
			.queryParam("page", page)
			.queryParam("limit", limit)
			.queryParam("keyword", keyword)
			.encode()
			.build()
			.toUri();
		// when
		ResponseEntity<List<FurnitureResponseDto>> response = testRestTemplate.exchange(
			url,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<>() {
			}
		);

		// then
		final List<FurnitureResponseDto> result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional.ofNullable(keyword).ifPresent((k) -> {
			assertThat(result).isNotNull();
			assertThat(result)
				.allMatch(item -> item.getFurnitureName().contains(k));
		});
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

	private static Stream<Arguments> provideSearchByCategoryParams() {
		return Stream.of(
			Arguments.of("1", "10", "1"),
			Arguments.of("2", "5", "2"),
			Arguments.of("3", "10", null),
			Arguments.of(null, null, "3"),
			Arguments.of(null, null, "4")
		);
	}

	private static Stream<Arguments> provideSearchByKeywordParams() {
		return Stream.of(
			Arguments.of("1", "10", "침대"),
			Arguments.of("2", "5", "책상"),
			Arguments.of("3", "10", null),
			Arguments.of(null, null, null),
			Arguments.of(null, null, "소파")
		);
	}

}
