package com.refurbmarket.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.refurbmarket.domain.Category;

@Mapper
public interface CategoryMapper {
	List<Category> findAll();
}
