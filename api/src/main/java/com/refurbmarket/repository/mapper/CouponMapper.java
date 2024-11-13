package com.refurbmarket.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.refurbmarket.domain.Coupon;

@Mapper
public interface CouponMapper {
	List<Coupon> findByIds(List<Long> idList);

	Optional<Coupon> findById(Long id);

	void updateIssuedQuantity(@Param("coupon") Coupon coupon);
}
