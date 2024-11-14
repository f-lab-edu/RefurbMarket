package com.refurbmarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.refurbmarket.dto.request.AvailableCouponsRequestDto;
import com.refurbmarket.dto.response.AvailableCouponsResponseDto;
import com.refurbmarket.dto.response.CouponResponseDto;
import com.refurbmarket.service.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Coupon", description = "Coupon API")
@RestController
@RequiredArgsConstructor
public class CouponController {
	private final CouponService couponService;

	@Operation(summary = "사용자의 쿠폰 전체 조회", description = "사용자가 보유하고 있는 모든 쿠폰을 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "사용자의 쿠폰 전체 조회 성공"
	)
	@GetMapping("/my-page/coupons")
	public List<CouponResponseDto> getAllCoupons(@RequestParam Long userId) {
		return couponService.getAllCoupons(userId);
	}

	@Operation(summary = "사용자 쿠폰 발급", description = "사용자의 쿠폰을 발급 한다.")
	@ApiResponse(
		responseCode = "200", description = "사용자 쿠폰 발급 성공"
	)
	@PostMapping("/event/{eventId}/coupon")
	public void issueCoupon(
		@PathVariable Long eventId,
		@RequestParam Long couponId,
		@RequestParam Long userId) {
		couponService.issueCoupon(eventId, couponId, userId);
	}

	@Operation(summary = "사용 가능한 쿠폰 조회", description = "사용자가 사용 가능한 쿠폰을 조회한다.")
	@ApiResponse(
		responseCode = "200", description = "사용 가능한 쿠폰 조회 성공"
	)
	@GetMapping("/orders/coupons")
	public List<AvailableCouponsResponseDto> getAvailableCoupons(@RequestBody AvailableCouponsRequestDto request) {
		return couponService.getAvailableCoupons(request);
	}
}
