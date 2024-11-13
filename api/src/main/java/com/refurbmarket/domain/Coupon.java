package com.refurbmarket.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coupon {
	private Long id;
	private Long eventId;
	private CouponType couponType;
	private DiscountType discountType; // 할인 종류(e.g. 정률, 정액..)
	private int value;
	private Long maxQuantity; // 일일 최대 발급 가능 쿠폰 수량, NULL일 경우 무제한 발급
	private Long issuedQuantity; // 일일 쿠폰 발급된 수량, NULL일 경우 무제한 발급
	private LocalDateTime validateStartDate; // 유효 시작 날짜
	private LocalDateTime validateEndDate; // 유효 종료 날짜
	private boolean isDuplicateIssuable; // 중복 발급 가능 여부

	private boolean availableIssueQuantity() {
		if (maxQuantity == null && issuedQuantity == null)
			return false;
		if (maxQuantity != null && issuedQuantity == null || maxQuantity == null && issuedQuantity != null)
			throw new RuntimeException("유효하지 않는 쿠폰입니다.");
		return maxQuantity >= issuedQuantity;
	}

	public void increaseIssuedQuantity() {
		if (availableIssueQuantity())
			issuedQuantity++;
	}
}
