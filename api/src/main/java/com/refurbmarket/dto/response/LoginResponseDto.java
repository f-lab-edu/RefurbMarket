package com.refurbmarket.dto.response;

import com.refurbmarket.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "로그인 응답")
@AllArgsConstructor
@Getter
public class LoginResponseDto {
	@Schema(description = "사용자 아이디")
	private Long id;
	@Schema(description = "사용자 이름")
	private String name;
	@Schema(description = "사용자 이메일")
	private String email;
	@Schema(description = "토큰")
	private String token;

	public static LoginResponseDto of(User user, String token) {
		return new LoginResponseDto(user.getId(), user.getName(), user.getEmail(), token);
	}
}
