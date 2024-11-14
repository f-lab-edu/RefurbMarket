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
public class AvailableCouponsResponseDto {
	private Long couponId;
	private Long couponIssueId;
	private String name;
	private DiscountType type;
	private int value;
	private Long totalDiscountPrice;
	private Long minOrderPrice;
	private Long maxDiscountPrice;
	private LocalDateTime validateStartDate;
	private LocalDateTime validateEndDate;

	public static AvailableCouponsResponseDto of(CouponIssue couponIssue, Coupon coupon, Event event,
		Long totalDiscountPrice) {
		return new AvailableCouponsResponseDto(
			coupon.getId(),
			couponIssue.getId(),
			event.getName(),
			coupon.getDiscountType(),
			coupon.getValue(),
			totalDiscountPrice,
			coupon.getMinOrderPrice(),
			coupon.getMaxDiscountPrice(),
			coupon.getValidateStartDate(),
			coupon.getValidateEndDate()
		);
	}
}
