package com.refurbmarket.repository.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.refurbmarket.domain.User;

@Mapper
public interface UserMapper {
	void insertUser(@Param("user") User user);

	Optional<User> findById(Long id);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
