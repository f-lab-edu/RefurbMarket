package com.refurbmarket.domain;

public enum OrderStatus {
	CANCELLED("취소됨"),
	PENDING("처리 중"),
	SHIPPED("배송 중"),
	DELIVERED("배송 완료");
	private final String description;

	OrderStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
