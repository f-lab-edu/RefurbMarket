package com.refurbmarket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.request.OrderRequestDto;
import com.refurbmarket.dto.response.OrderResponseDto;
import com.refurbmarket.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Order", description = "Order API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

	@Operation(summary = "주문 및 결제", description = "주문 후 결제를 진행한다.")
	@ApiResponse(
		responseCode = "200", description = "주문 및 결제 성공"
	)
	@PostMapping
	public OrderResponseDto order(@RequestBody OrderRequestDto request) {
		return orderService.order(request);
	}
}
