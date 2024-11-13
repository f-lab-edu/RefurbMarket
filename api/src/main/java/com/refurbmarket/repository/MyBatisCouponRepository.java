package com.refurbmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.Coupon;
import com.refurbmarket.repository.mapper.CouponMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCouponRepository {
	private final CouponMapper couponMapper;

	public List<Coupon> findByIds(List<Long> idList) {
		return couponMapper.findByIds(idList);
	}

	public Optional<Coupon> findById(Long id) {
		return couponMapper.findById(id);
	}

	public void updateIssuedQuantity(Coupon coupon) {
		couponMapper.updateIssuedQuantity(coupon);
	}
}
