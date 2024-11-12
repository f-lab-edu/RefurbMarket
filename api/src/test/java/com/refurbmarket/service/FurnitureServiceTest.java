package com.refurbmarket.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.refurbmarket.domain.Furniture;
import com.refurbmarket.domain.Seller;
import com.refurbmarket.dto.response.FurnitureResponseDto;
import com.refurbmarket.repository.MyBatisFurnitureRepository;
import com.refurbmarket.repository.MyBatisSellerRepository;

@ExtendWith(MockitoExtension.class)
public class FurnitureServiceTest {
	@InjectMocks
	FurnitureService furnitureService;
	@Mock
	MyBatisFurnitureRepository furnitureRepository;
	@Mock
	MyBatisSellerRepository sellerRepository;

	@DisplayName("검색어 기반 가구 조회 성공")
	@Test
	public void successFulSearchByKeyword() {
		//given
		String keyword = "서랍";
		doReturn(getFurnitureList()).when(furnitureRepository)
			.findFurnitureByKeywordWithPaging(anyInt(), anyInt(), anyString());
		doReturn(getSellers()).when(sellerRepository).findSellersByIds(anyList());
		//when
		List<FurnitureResponseDto> result = furnitureService.getFurnitureListByKeyword(anyInt(), anyInt(), anyString());
		//then
		assertThat(result).hasSize(2);
		assertThat(result).allMatch(item -> item.getFurnitureName().contains(keyword));
	}

	@DisplayName("검색어 기반 가구 조회 실패_판매자가 존재하지 않음")
	@Test
	public void failOnSellerNotFoundSearchByKeyword() {
		//given
		doReturn(getFurnitureList()).when(furnitureRepository)
			.findFurnitureByKeywordWithPaging(anyInt(), anyInt(), anyString());
		doReturn(List.of()).when(sellerRepository).findSellersByIds(anyList());
		//when
		RuntimeException result = assertThrows(RuntimeException.class,
			() -> furnitureService.getFurnitureListByKeyword(anyInt(), anyInt(), anyString()));
		//then
		assertThat(result.getMessage()).isEqualTo("판매자가 존재하지 않습니다.");
	}

	@DisplayName("전체/카테고리별 가구 조회 성공")
	@Test
	public void successFulSearchByCategory() {
		//given
		doReturn(getFurnitureList()).when(furnitureRepository)
			.findFurnitureByCategoryIdWithPaging(anyInt(), anyInt(), anyLong());
		doReturn(getSellers()).when(sellerRepository).findSellersByIds(anyList());
		//when
		List<FurnitureResponseDto> result = furnitureService.getFurnitureListByCategory(anyInt(), anyInt(), anyLong());
		//then
		assertThat(result).hasSize(2);
	}

	@DisplayName("전체/카테고리별 가구 조회 실패_판매자가 존재하지 않음")
	@Test
	public void failOnSellerNotFoundSearchByCategory() {
		//given
		doReturn(getFurnitureList()).when(furnitureRepository)
			.findFurnitureByCategoryIdWithPaging(anyInt(), anyInt(), anyLong());
		doReturn(List.of()).when(sellerRepository).findSellersByIds(anyList());
		//when
		RuntimeException result = assertThrows(RuntimeException.class,
			() -> furnitureService.getFurnitureListByCategory(anyInt(), anyInt(), anyLong()));
		//then
		assertThat(result.getMessage()).isEqualTo("판매자가 존재하지 않습니다.");
	}

	@DisplayName("특정 가구 상세 정보 조회 성공")
	@Test
	public void successFulGetFurniture() {
		//given
		Furniture furniture = getFurnitureList().get(0);
		doReturn(Optional.of(furniture)).when(furnitureRepository)
			.findById(anyLong());
		doReturn(Optional.of(getSellers().get(0))).when(sellerRepository)
			.findById(anyLong());
		//when
		FurnitureResponseDto result = furnitureService.getFurnitureById(anyLong());
		//then
		assertThat(result).isNotNull();
		assertThat(result.getSellerId()).isEqualTo(furniture.getSellerId());
	}

	@DisplayName("특정 가구 상세 정보 조회 실패_판매자가 존재하지 않음")
	@Test
	public void failOnSellerNotFoundGetFurniture() {
		//given
		doReturn(Optional.of(getFurnitureList().get(0))).when(furnitureRepository)
			.findById(anyLong());
		doReturn(Optional.empty()).when(sellerRepository)
			.findById(anyLong());
		//when
		RuntimeException result = assertThrows(RuntimeException.class,
			() -> furnitureService.getFurnitureById(anyLong()));
		//then
		assertThat(result.getMessage()).isEqualTo("판매자가 존재하지 않습니다.");
	}

	@DisplayName("특정 가구 상세 정보 조회 실패_상품이 존재하지 않음")
	@Test
	public void failOnFurnitureNotFoundGetFurniture() {
		//given
		doReturn(Optional.empty()).when(furnitureRepository)
			.findById(anyLong());
		//when
		RuntimeException result = assertThrows(RuntimeException.class,
			() -> furnitureService.getFurnitureById(anyLong()));
		//then
		assertThat(result.getMessage()).isEqualTo("상품이 존재하지 않습니다.");
	}

	private List<Furniture> getFurnitureList() {
		return List.of(
			new Furniture(
				1L,
				2L,
				18L,
				"샘베딩 스테디 서랍장 5단",
				"https://aaa.com/image4",
				100000000L,
				200,
				10000),
			new Furniture(
				2L,
				2L,
				18L,
				"크린트 모던 높은 거실장 120cm 서랍형 수납장",
				"https://aaa.com/image8",
				1000000L,
				2000,
				4000)
		);
	}

	private List<Seller> getSellers() {
		return List.of(
			new Seller(2L,
				"김둘",
				"까사미아",
				"casamia@email.com",
				"asdf",
				"01022222222")
		);
	}
}
