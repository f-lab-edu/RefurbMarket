package com.refurbmarket.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.refurbmarket.domain.Furniture;

@Mapper
public interface FurnitureMapper {
	List<Furniture> findFurnitureByCategoryIdWithPaging(int offset, int limit, Long categoryId);

	List<Furniture> findFurnitureByKeywordWithPaging(int offset, int limit, String keyword);

	Optional<Furniture> findById(Long id);

	List<Furniture> findByIds(List<Long> idList);

}
