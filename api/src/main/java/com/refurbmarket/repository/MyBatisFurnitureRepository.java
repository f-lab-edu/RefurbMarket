package com.refurbmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.Furniture;
import com.refurbmarket.repository.mapper.FurnitureMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisFurnitureRepository {
	private final FurnitureMapper furnitureMapper;

	public List<Furniture> findFurnitureByCategoryIdWithPaging(int offset, int limit, Long categoryId) {
		return furnitureMapper.findFurnitureByCategoryIdWithPaging(offset, limit, categoryId);
	}

	public List<Furniture> findFurnitureByKeywordWithPaging(int offset, int limit, String keyword) {
		return furnitureMapper.findFurnitureByKeywordWithPaging(offset, limit, keyword);
	}

	public Optional<Furniture> findById(Long id) {
		return furnitureMapper.findById(id);
	}
}
