package com.refurbmarket.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.refurbmarket.domain.Category;
import com.refurbmarket.dto.response.CategoryResponseDto;
import com.refurbmarket.repository.MyBatisCategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	@InjectMocks
	CategoryService categoryService;
	@Mock
	MyBatisCategoryRepository categoryRepository;

	@DisplayName("모든 카테고리 목록 조회 성공")
	@Test
	public void successfulGetAllCategories() {
		//given
		doReturn(getCategories()).when(categoryRepository).findAll();
		//when
		List<CategoryResponseDto> result = categoryService.getCategories();
		//then
		assertThat(result).isNotNull();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getChildren()).hasSize(1);
		assertThat(result.get(1).getChildren()).hasSize(2);
		assertThat(result.get(0).getChildren().get(0).getChildren()).hasSize(1);
		assertThat(result.get(1).getChildren().get(0).getChildren()).hasSize(2);
		assertThat(result.get(1).getChildren().get(1).getChildren()).hasSize(0);
	}

	private List<Category> getCategories() {
		return List.of(
			new Category(1L, 1, null, "침대"),
			new Category(2L, 1, null, "매트리스·토퍼"),
			new Category(3L, 2, 1L, "침대프레임"),
			new Category(4L, 2, 1L, "침대부속가구"),
			new Category(5L, 2, 2L, "매트리스"),
			new Category(6L, 3, 3L, "일반침대"),
			new Category(7L, 3, 3L, "저상형침대"),
			new Category(8L, 3, 5L, "스프링매트리스")
		);
	}
}
