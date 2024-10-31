package com.refurbmarket.repository.interfaces;

import java.util.Optional;

import com.refurbmarket.domain.User;

public interface UserRepository {
	User insertUser(User user);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
}
