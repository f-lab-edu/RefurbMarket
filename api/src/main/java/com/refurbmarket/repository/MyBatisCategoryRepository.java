package com.refurbmarket.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.Category;
import com.refurbmarket.repository.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCategoryRepository {
	private final CategoryMapper categoryMapper;

	public List<Category> findAll() {
		return categoryMapper.findAll();
	}
}
