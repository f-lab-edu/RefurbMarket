package com.refurbmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {
	private Long id;
	private int depth;
	private Long parentId;
	private String name;
}
