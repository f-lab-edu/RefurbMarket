package com.refurbmarket.dto.request;

import com.refurbmarket.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "로그인 요청")
@Getter
@AllArgsConstructor
public class LoginRequestDto {
	@Schema(description = "회원 이메일")
	private String email;
	@Schema(description = "회원 비밀번호")
	private String password;

	public User toDomain() {
		return new User(null, null, email, password, null);
	}
}
