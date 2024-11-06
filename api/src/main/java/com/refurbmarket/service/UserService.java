package com.refurbmarket.service;

import org.springframework.stereotype.Service;

import com.refurbmarket.common.util.TokenProvider;
import com.refurbmarket.domain.User;
import com.refurbmarket.dto.user.LoginRequestDto;
import com.refurbmarket.dto.user.LoginResponseDto;
import com.refurbmarket.dto.user.SignUpRequestDto;
import com.refurbmarket.repository.MyBatisUserRepository;
import com.refurbmarket.repository.RedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final MyBatisUserRepository userRepository;
	private final TokenProvider tokenProvider;
	private final RedisRepository redisRepository;

	public LoginResponseDto createUser(SignUpRequestDto request) {
		User user = request.toDomain();
		checkEmailDuplication(user.getEmail());
		user = userRepository.insertUser(user);
		String token = generateAndStoreToken(user);
		return LoginResponseDto.of(user, token);
	}

	public LoginResponseDto login(LoginRequestDto request) {
		User user = request.toDomain();
		user = validateUserCredentials(user);
		String token = generateAndStoreToken(user);
		return LoginResponseDto.of(user, token);
	}

	private String generateAndStoreToken(User user) {
		String token = tokenProvider.generateToken();
		redisRepository.set(token, user.getId().toString());
		return token;
	}

	private User validateUserCredentials(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow(() ->
			new IllegalArgumentException("로그인 정보가 올바르지 않습니다.")
		);
	}

	private void checkEmailDuplication(String email) {
		userRepository.findByEmail(email).ifPresent(user -> {
			throw new IllegalArgumentException("이메일 중복입니다.");
		});
	}
}
