package com.refurbmarket.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.User;
import com.refurbmarket.repository.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository {
	private final UserMapper userMapper;

	public User insertUser(User user) {
		userMapper.insertUser(user);
		return user;
	}

	public Optional<User> findByEmail(String email) {
		return userMapper.findByEmail(email);
	}

	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userMapper.findByEmailAndPassword(email, password);
	}
}
