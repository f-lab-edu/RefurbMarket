package com.refurbmarket.dto.request;

import java.util.List;

public class OrderRequestDto {
	private Long id;
	private Long userId;
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	private List<Long> couponIssueIdList;
	private List<OrderItemRequestDto> orderItemList;
}
