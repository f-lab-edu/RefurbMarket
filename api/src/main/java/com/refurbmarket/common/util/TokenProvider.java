package com.refurbmarket.common.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
	public String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
