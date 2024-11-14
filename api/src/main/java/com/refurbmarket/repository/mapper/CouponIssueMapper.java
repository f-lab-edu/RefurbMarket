package com.refurbmarket.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.refurbmarket.domain.CouponIssue;

@Mapper
public interface CouponIssueMapper {
	List<CouponIssue> findByUserId(Long userId);

	List<CouponIssue> findByUserIdAndCouponId(Long userId, Long couponId);

	void insertCouponIssue(@Param("couponIssue") CouponIssue couponIssue);
}
