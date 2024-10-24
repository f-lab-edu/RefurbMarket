package com.refurbmarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.user.LoginRequestDto;
import com.refurbmarket.dto.user.LoginResponseDto;
import com.refurbmarket.dto.user.SignUpRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User API")
@RestController
@RequestMapping("/users")
public class UserController {
	@Operation(summary = "회원 등록", description = "사용자는 회원가입을 할 수 있다.")
	@ApiResponse(
		responseCode = "201", description = "회원 등록 성공"
	)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@RequestBody final SignUpRequestDto request) {
	}

	@Operation(summary = "로그인", description = "회원은 로그인을 할 수 있다.")
	@ApiResponse(
		responseCode = "200", description = "로그인 성공"
	)
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody final LoginRequestDto request) {
		return new LoginResponseDto("abcdefg");
	}
}
