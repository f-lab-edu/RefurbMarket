package com.refurbmarket.dto.request;

import com.refurbmarket.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "회원 가입 요청")
@Getter
@AllArgsConstructor
public class SignUpRequestDto {
	@Schema(description = "사용자 이름")
	private String name;
	@Schema(description = "사용자 이메일")
	private String email;
	@Schema(description = "사용자 비밀번호")
	private String password;
	@Schema(description = "사용자 휴대폰 번호")
	private String phoneNumber;

	public User toDomain() {
		return new User(null, name, email, password, phoneNumber);
	}
}
