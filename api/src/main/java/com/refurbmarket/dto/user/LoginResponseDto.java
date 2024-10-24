package com.refurbmarket.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

@Schema(description = "로그인 응답")
@AllArgsConstructor
public class LoginResponseDto {
	@Schema(description = "토큰")
	private String token;
}
