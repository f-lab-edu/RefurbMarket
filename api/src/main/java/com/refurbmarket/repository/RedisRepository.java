package com.refurbmarket.repository;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisRepository {
	private final RedisTemplate<String, String> redisTemplate;

	public Optional<String> get(String token) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(token));
	}

	public void set(String token, String email) {
		redisTemplate.opsForValue().set(token, email);
	}
}
