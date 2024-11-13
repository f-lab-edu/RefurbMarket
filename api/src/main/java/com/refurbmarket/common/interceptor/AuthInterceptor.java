package com.refurbmarket.common.interceptor;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.refurbmarket.common.define.HeaderKey;
import com.refurbmarket.repository.RedisRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	private final RedisRepository redisRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = extractToken(request);
		validateToken(token);
		return true;
	}

	private String extractToken(HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
			.filter(token -> token.startsWith(HeaderKey.BEARER))
			.map(token -> token.replace(HeaderKey.BEARER, ""))
			.orElseThrow(() -> new RuntimeException("인증 헤더가 없거나 잘못되었습니다."));
	}

	private void validateToken(String token) {
		redisRepository.get(token)
			.orElseThrow(() -> new RuntimeException("유효하지 않거나 만료된 토큰입니다."));
	}
}
