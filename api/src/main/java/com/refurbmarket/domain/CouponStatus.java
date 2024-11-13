package com.refurbmarket.domain;

public enum CouponStatus {
	NOT_ACTIVE("유효기간 시작 전"),
	ACTIVE("사용 가능"),
	USED("사용 완료"),
	EXPIRED("기간 만료");
	private final String description;

	CouponStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
