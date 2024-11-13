package com.refurbmarket.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.refurbmarket.domain.Category;
import com.refurbmarket.dto.response.CategoryResponseDto;
import com.refurbmarket.repository.MyBatisCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final MyBatisCategoryRepository categoryRepository;

	public List<CategoryResponseDto> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		Map<Long, CategoryResponseDto> dtoMap = createDtoMap(categories);
		return createTreeStructure(categories, dtoMap);
	}

	private List<CategoryResponseDto> createTreeStructure(List<Category> categories,
		Map<Long, CategoryResponseDto> dtoMap) {
		return categories.stream()
			.peek(category -> {
				CategoryResponseDto dto = dtoMap.get(category.getId());
				Optional.ofNullable(category.getParentId())
					.ifPresent(parentId -> dtoMap.get(parentId).getChildren().add(dto));
			})
			.filter(category -> category.getParentId() == null)
			.sorted(Comparator.comparing(Category::getName))
			.map(category -> dtoMap.get(category.getId()))
			.collect(toList());
	}

	private Map<Long, CategoryResponseDto> createDtoMap(List<Category> categories) {
		return categories.stream()
			.collect(toMap(Category::getId, CategoryResponseDto::of));
	}
}
