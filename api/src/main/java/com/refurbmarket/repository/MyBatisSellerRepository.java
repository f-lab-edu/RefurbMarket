package com.refurbmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.Seller;
import com.refurbmarket.repository.mapper.SellerMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisSellerRepository {
	private final SellerMapper sellerMapper;

	public List<Seller> findSellersByIds(List<Long> idList) {
		return sellerMapper.findSellersByIds(idList);
	}

	public Optional<Seller> findById(Long id) {
		return sellerMapper.findById(id);
	}
}
