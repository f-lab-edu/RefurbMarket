package com.refurbmarket.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponIssue {
	private Long id;
	private Long userId;
	private Long couponId;
	private CouponStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static CouponIssue create(Long userId, Long couponId, LocalDateTime currentDateTime) {
		return new CouponIssue(null, userId, couponId, CouponStatus.NOT_ACTIVE, currentDateTime, currentDateTime);
	}
}
