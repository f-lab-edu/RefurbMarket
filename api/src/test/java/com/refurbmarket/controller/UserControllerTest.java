package com.refurbmarket.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refurbmarket.dto.user.LoginRequestDto;
import com.refurbmarket.dto.user.SignUpRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	@Autowired
	private UserController userController;
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.build();
	}

	@DisplayName("회원가입 성공")
	@Test
	public void successSignUp() throws Exception {
		// given
		final String url = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signUpRequest()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isCreated());
	}

	@DisplayName("로그인 성공")
	@Test
	public void successLogin() throws Exception {
		// given
		final String url = "/users/login";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signInRequest()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(status().isOk());
	}

	private SignUpRequestDto signUpRequest() {
		return new SignUpRequestDto("김테스트", "test@email.com", "testtest12!");
	}

	private LoginRequestDto signInRequest() {
		return new LoginRequestDto("test@email.com", "testtest12!");
	}
}
