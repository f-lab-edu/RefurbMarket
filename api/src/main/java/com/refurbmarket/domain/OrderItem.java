package com.refurbmarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
	private Long id;
	private Long userId;
	private Long couponId;
	private Long couponIssueId;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private OrderStatus status;
}
