package com.refurbmarket.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "로그인 요청")
@Getter
public class LoginRequestDto {
	@Schema(description = "회원 이메일")
	private String email;
	@Schema(description = "회원 비밀번호")
	private String password;
}
