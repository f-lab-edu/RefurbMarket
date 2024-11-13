package com.refurbmarket.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.refurbmarket.domain.Seller;

@Mapper
public interface SellerMapper {
	List<Seller> findSellersByIds(List<Long> idList);

	Optional<Seller> findById(Long id);
}
