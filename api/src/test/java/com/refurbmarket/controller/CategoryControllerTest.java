package com.refurbmarket.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refurbmarket.dto.category.CategoryResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
	@Autowired
	private CategoryController categoryController;
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
			.build();
	}

	@DisplayName("모든 카테고리 목록 조회 성공")
	@Test
	public void getCategoryList() throws Exception {
		// given
		final String url = "/categories";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
		);
		// then
		MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
		List<CategoryResponseDto> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(
			StandardCharsets.UTF_8), List.class);
		assertEquals(response.size(), 2);
	}

}
