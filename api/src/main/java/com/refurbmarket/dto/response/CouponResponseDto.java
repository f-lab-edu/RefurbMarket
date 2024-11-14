package com.refurbmarket.dto.response;

import java.time.LocalDateTime;

import com.refurbmarket.domain.Coupon;
import com.refurbmarket.domain.CouponIssue;
import com.refurbmarket.domain.DiscountType;
import com.refurbmarket.domain.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CouponResponseDto {
	private Long couponIssueId;
	private String name;
	private DiscountType type;
	private int discount;
	private LocalDateTime validateStartDate;
	private LocalDateTime validateEndDate;
	private boolean isUsed;

	public static CouponResponseDto of(CouponIssue couponIssue, Coupon coupon, Event event) {
		return new CouponResponseDto(
			couponIssue.getId(),
			event.getName(),
			coupon.getDiscountType(),
			coupon.getValue(),
			coupon.getValidateStartDate(),
			coupon.getValidateEndDate(),
			couponIssue.isUsed()
		);
	}
}
