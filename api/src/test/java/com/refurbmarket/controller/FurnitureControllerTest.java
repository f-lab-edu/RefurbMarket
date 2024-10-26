package com.refurbmarket.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refurbmarket.dto.furniture.FurnitureListResponseDto;
import com.refurbmarket.dto.furniture.FurnitureResponseDto;
import com.refurbmarket.dto.furniture.FurnitureSearchByCategoryRequestDto;
import com.refurbmarket.dto.furniture.FurnitureSearchByKeywordRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class FurnitureControllerTest {
	@Autowired
	private FurnitureController furnitureController;
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(furnitureController)
			.build();
	}

	@DisplayName("전체/카테고리별 가구 조회 성공")
	@Test
	public void searchByCategory() throws Exception {
		// given
		final String url = "/furniture/search/category";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
				.content(objectMapper.writeValueAsString(furnitureSearchByCategoryRequestDto()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
		FurnitureListResponseDto response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(
			StandardCharsets.UTF_8), FurnitureListResponseDto.class);
		assertEquals(response.getList().size(), 2);
	}

	@DisplayName("검색어 기반 가구 조회 성공")
	@Test
	public void searchByKeyword() throws Exception {
		// given
		final String url = "/furniture/search/keyword";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
				.content(objectMapper.writeValueAsString(furnitureSearchByKeywordRequestDto()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
		FurnitureListResponseDto response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(
			StandardCharsets.UTF_8), FurnitureListResponseDto.class);
		assertTrue(response.getList().get(0).getName().contains("침대 프레임"));
		assertTrue(response.getList().get(1).getName().contains("침대 프레임"));

	}

	@DisplayName("특정 가구 상세 정보 조회 성공")
	@Test
	public void getFurniture() throws Exception {
		// given
		final Long furnitureId = 1L;
		final String url = "/furniture/{id}";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.get(url, furnitureId)
		);
		// then
		MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
		FurnitureResponseDto response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(
			StandardCharsets.UTF_8), FurnitureResponseDto.class);
		assertEquals(response.getFurnitureId(), furnitureId);
	}

	private FurnitureSearchByKeywordRequestDto furnitureSearchByKeywordRequestDto() {
		return new FurnitureSearchByKeywordRequestDto("침대 프레임", 10, 0);
	}

	private FurnitureSearchByCategoryRequestDto furnitureSearchByCategoryRequestDto() {
		return new FurnitureSearchByCategoryRequestDto(1L, 10, 0);
	}

}
