package com.refurbmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seller {
	private Long id;
	private String name;
	private String storeName;
	private String email;
	private String password;
	private String phoneNumber;
}
