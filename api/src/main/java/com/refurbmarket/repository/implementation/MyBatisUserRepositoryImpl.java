package com.refurbmarket.repository.implementation;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.User;
import com.refurbmarket.repository.interfaces.UserRepository;
import com.refurbmarket.repository.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepositoryImpl implements UserRepository {
	private final UserMapper userMapper;

	@Override
	public User insertUser(User user) {
		userMapper.insertUser(user);
		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userMapper.findByEmail(email);
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userMapper.findByEmailAndPassword(email, password);
	}
}
