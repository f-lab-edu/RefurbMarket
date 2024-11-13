package com.refurbmarket.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.refurbmarket.domain.Furniture;
import com.refurbmarket.domain.Seller;
import com.refurbmarket.dto.response.FurnitureResponseDto;
import com.refurbmarket.repository.MyBatisFurnitureRepository;
import com.refurbmarket.repository.MyBatisSellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FurnitureService {
	private final MyBatisFurnitureRepository furnitureRepository;
	private final MyBatisSellerRepository sellerRepository;

	public List<FurnitureResponseDto> getFurnitureListByCategory(int page, int limit, Long categoryId) {
		return getFurnitureList(
			furnitureRepository.findFurnitureByCategoryIdWithPaging(getOffset(page, limit), limit, categoryId));
	}

	public List<FurnitureResponseDto> getFurnitureListByKeyword(int page, int limit, String keyword) {
		return getFurnitureList(
			furnitureRepository.findFurnitureByKeywordWithPaging(getOffset(page, limit), limit, keyword));
	}

	public FurnitureResponseDto getFurnitureById(Long id) {
		Furniture furniture = getFurniture(id);
		Seller seller = getSeller(furniture);
		return FurnitureResponseDto.of(furniture, seller);
	}

	private Furniture getFurniture(Long id) {
		return furnitureRepository.findById(id).orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
	}

	private List<FurnitureResponseDto> getFurnitureList(List<Furniture> furnitureList) {
		if (furnitureList.isEmpty())
			return Collections.emptyList();

		Map<Long, Seller> sellerMap = createSellerMap(furnitureList);
		return furnitureList.stream()
			.map(furniture -> FurnitureResponseDto.of(furniture, getSeller(sellerMap, furniture)))
			.collect(toList());
	}

	private Map<Long, Seller> createSellerMap(List<Furniture> furnitureList) {
		List<Long> sellerIdList = furnitureList.stream()
			.map(Furniture::getSellerId)
			.distinct()
			.collect(toList());

		return sellerRepository.findSellersByIds(sellerIdList)
			.stream()
			.collect(toMap(Seller::getId, seller -> seller));
	}

	private Seller getSeller(Furniture furniture) {
		return sellerRepository.findById(furniture.getSellerId())
			.orElseThrow(() -> new RuntimeException("판매자가 존재하지 않습니다."));
	}

	private Seller getSeller(Map<Long, Seller> sellerMap, Furniture furniture) {
		return Optional.ofNullable(sellerMap.get(furniture.getSellerId()))
			.orElseThrow(() -> new RuntimeException("판매자가 존재하지 않습니다."));
	}

	private int getOffset(int page, int limit) {
		return (page - 1) * limit;
	}
}
