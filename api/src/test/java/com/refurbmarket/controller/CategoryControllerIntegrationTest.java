package com.refurbmarket.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.refurbmarket.dto.category.CategoryResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerIntegrationTest {
	@Autowired
	private TestRestTemplate testRestTemplate;

	@DisplayName("모든 카테고리 목록 조회 성공")
	@Test
	public void getCategoryList() throws Exception {
		// given
		final String url = "/categories";
		// when
		final ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);
		// then
		final List<CategoryResponseDto> result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result).hasSize(2);
	}

}
