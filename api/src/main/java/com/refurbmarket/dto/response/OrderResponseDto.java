package com.refurbmarket.dto.request;

import java.util.List;

import com.refurbmarket.dto.PaymentType;

import lombok.Getter;

@Getter
public class OrderRequestDto {
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private List<Long> couponIssueIdList;
	private List<OrderItemRequestDto> orderItemList;
	private PaymentType type;
}
