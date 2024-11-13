package com.refurbmarket.service;

import org.springframework.stereotype.Service;

import com.refurbmarket.dto.request.OrderRequestDto;
import com.refurbmarket.dto.response.OrderResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	public OrderResponseDto order(OrderRequestDto dto) {
		// 쿠폰이 유효한지 확인

		// 주문할 상품 리스트 조회

		// 주문할 상품 재고 확인

		// 주문 생성

		// 결제
		return null;
	}
}
