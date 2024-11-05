package com.refurbmarket.service;

import org.springframework.stereotype.Service;

import com.refurbmarket.domain.User;
import com.refurbmarket.dto.user.LoginRequestDto;
import com.refurbmarket.dto.user.LoginResponseDto;
import com.refurbmarket.dto.user.SignUpRequestDto;
import com.refurbmarket.repository.implementation.MyBatisUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final MyBatisUserRepository userRepository;

	public LoginResponseDto createUser(SignUpRequestDto request) {
		User user = request.toDomain();
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new IllegalArgumentException("이메일 중복");
		}
		user = userRepository.insertUser(user);
		return LoginResponseDto.of(user, "asdf");
	}

	public LoginResponseDto login(LoginRequestDto request) {
		User user = request.toDomain();
		if (userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).isEmpty()) {
			throw new IllegalArgumentException("로그인 정보가 올바르지 않습니다.");
		}
		return LoginResponseDto.of(user, "asdf");
	}
}
