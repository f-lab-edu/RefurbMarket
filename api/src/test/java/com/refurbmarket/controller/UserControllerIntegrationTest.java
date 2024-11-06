package com.refurbmarket.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.refurbmarket.dto.request.LoginRequestDto;
import com.refurbmarket.dto.request.SignUpRequestDto;
import com.refurbmarket.dto.response.LoginResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
	@Autowired
	TestRestTemplate testRestTemplate;

	@DisplayName("회원가입 성공")
	@Test
	public void successSignUp() throws Exception {
		// given
		final String url = "/users";
		final SignUpRequestDto request = signUpRequest();
		// when
		final ResponseEntity<LoginResponseDto> response = testRestTemplate.postForEntity(url, request,
			LoginResponseDto.class);
		// then
		final LoginResponseDto result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getName()).isEqualTo(request.getName());
		assertThat(result.getEmail()).isEqualTo(request.getEmail());
	}

	@DisplayName("로그인 성공")
	@Test
	public void successLogin() throws Exception {
		// given
		final String url = "/users/login";
		final LoginRequestDto request = loginRequest();
		// when
		final ResponseEntity<LoginResponseDto> response = testRestTemplate.postForEntity(url, request,
			LoginResponseDto.class);
		// then
		final LoginResponseDto result = response.getBody();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getEmail()).isEqualTo(request.getEmail());
	}

	private SignUpRequestDto signUpRequest() {
		return new SignUpRequestDto("김테스트", "test@test.com", "testtest12!");
	}

	private LoginRequestDto loginRequest() {
		return new LoginRequestDto("test@test.com", "testtest12!");
	}
}
