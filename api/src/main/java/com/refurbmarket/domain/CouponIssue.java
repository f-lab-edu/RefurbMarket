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
	private boolean isUsed;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static CouponIssue create(Long userId, Long couponId, LocalDateTime currentDateTime) {
		return new CouponIssue(null, userId, couponId, false, currentDateTime, currentDateTime);
	}

	public boolean isCouponIssuedToday(LocalDateTime currentDateTime) {
		return createdAt.toLocalDate().equals(currentDateTime.toLocalDate());
	}
}
