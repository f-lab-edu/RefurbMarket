package com.refurbmarket.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.CouponIssue;
import com.refurbmarket.repository.mapper.CouponIssueMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCouponIssueRepository {
	private final CouponIssueMapper couponIssueMapper;

	public List<CouponIssue> findByUserId(Long userId) {
		return couponIssueMapper.findByUserId(userId);
	}

	public List<CouponIssue> findByUserIdAndCouponId(Long userId, Long couponId) {
		return couponIssueMapper.findByUserIdAndCouponId(userId, couponId);
	}

	public void insertCouponIssue(CouponIssue couponIssue) {
		couponIssueMapper.insertCouponIssue(couponIssue);
	}
}
