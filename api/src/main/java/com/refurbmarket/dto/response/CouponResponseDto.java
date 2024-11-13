package com.refurbmarket.dto.response;

import com.refurbmarket.domain.Coupon;
import com.refurbmarket.domain.CouponIssue;
import com.refurbmarket.domain.CouponStatus;
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
	private CouponStatus couponStatus;

	public static CouponResponseDto of(CouponIssue couponIssue, Coupon coupon, Event event) {
		return new CouponResponseDto(
			couponIssue.getId(), event.getName(), coupon.getDiscountType(), coupon.getValue(), couponIssue.getStatus());
	}
}
