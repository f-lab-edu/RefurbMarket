package com.refurbmarket.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.refurbmarket.domain.Coupon;
import com.refurbmarket.domain.CouponIssue;
import com.refurbmarket.domain.Event;
import com.refurbmarket.domain.Furniture;
import com.refurbmarket.dto.request.AvailableCouponsRequestDto;
import com.refurbmarket.dto.response.AvailableCouponsResponseDto;
import com.refurbmarket.dto.response.CouponResponseDto;
import com.refurbmarket.repository.MyBatisCouponIssueRepository;
import com.refurbmarket.repository.MyBatisCouponRepository;
import com.refurbmarket.repository.MyBatisEventRepository;
import com.refurbmarket.repository.MyBatisFurnitureRepository;
import com.refurbmarket.repository.MyBatisUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {
	private final MyBatisCouponRepository couponRepository;
	private final MyBatisCouponIssueRepository couponIssueRepository;
	private final MyBatisFurnitureRepository furnitureRepository;
	private final MyBatisEventRepository eventRepository;
	private final MyBatisUserRepository userRepository;

	public List<AvailableCouponsResponseDto> getAvailableCoupons(AvailableCouponsRequestDto request) {
		validateUser(request.getUserId());
		Long totalPrice = calculateTotalPrice(request);

		List<CouponIssue> couponIssueList = getUnusedCoupons(request);
		if (couponIssueList.isEmpty())
			return Collections.emptyList();

		Set<Long> eventIdList = new HashSet<>();
		Map<Long, Coupon> couponMap = createCouponMap(eventIdList, couponIssueList);
		Map<Long, Event> eventMap = createEventMap(eventIdList);

		return couponIssueList.stream().map(couponIssue -> {
				Coupon coupon = getCoupon(couponMap, couponIssue);
				Event event = getEvent(eventMap, coupon);
				return coupon.checkAvailability(LocalDateTime.now(), totalPrice)
					? AvailableCouponsResponseDto.of(couponIssue, coupon, event, coupon.calculateDiscountPrice(totalPrice))
					: null;
			})
			.filter(Objects::nonNull)
			.collect(toList());
	}

	private Long calculateTotalPrice(AvailableCouponsRequestDto request) {
		Map<Long, Integer> furnitureQuantityMap = request.getFurnitureQuantityMap();
		List<Long> furnitureIds = new ArrayList<>(furnitureQuantityMap.keySet());
		List<Furniture> furnitureList = validateAndGetFurniture(furnitureIds);
		return furnitureList.stream()
			.mapToLong(furniture -> furniture.getPrice() * furnitureQuantityMap.get(furniture.getId()))
			.sum();
	}

	private List<Furniture> validateAndGetFurniture(List<Long> furnitureIdList) {
		List<Furniture> furnitureList = furnitureRepository.findByIds(furnitureIdList);
		if (furnitureList.size() != furnitureIdList.size()) {
			throw new RuntimeException("존재하지 않는 상품이 있습니다.");
		}
		return furnitureList;
	}

	private List<CouponIssue> getUnusedCoupons(AvailableCouponsRequestDto request) {
		return couponIssueRepository.findByUserId(request.getUserId())
			.stream()
			.filter(couponIssue -> !couponIssue.isUsed())
			.collect(toList());
	}

	public List<CouponResponseDto> getAllCoupons(Long userId) {
		validateUser(userId);

		List<CouponIssue> couponIssueList = couponIssueRepository.findByUserId(userId);
		if (couponIssueList.isEmpty())
			return Collections.emptyList();

		Set<Long> eventIdList = new HashSet<>();
		Map<Long, Coupon> couponMap = createCouponMap(eventIdList, couponIssueList);
		Map<Long, Event> eventMap = createEventMap(eventIdList);

		return couponIssueList.stream().map(couponIssue -> {
			Coupon coupon = getCoupon(couponMap, couponIssue);
			Event event = getEvent(eventMap, coupon);
			return CouponResponseDto.of(couponIssue, coupon, event);
		}).collect(toList());
	}

	private void validateUser(Long userId) {
		userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
	}

	private Map<Long, Coupon> createCouponMap(Set<Long> eventIdList, List<CouponIssue> couponIssueList) {
		Set<Long> couponIdList = couponIssueList.stream()
			.map(CouponIssue::getCouponId)
			.collect(toSet());
		return couponRepository.findByIds(new ArrayList<>(couponIdList)).stream()
			.peek(coupon -> eventIdList.add(coupon.getEventId()))
			.collect(toMap(Coupon::getId, coupon -> coupon));
	}

	private Map<Long, Event> createEventMap(Set<Long> eventIdList) {
		return eventRepository.findByIds(new ArrayList<>(eventIdList)).stream()
			.collect(toMap(Event::getId, event -> event));
	}

	private Coupon getCoupon(Map<Long, Coupon> couponMap, CouponIssue couponIssue) {
		return Optional.ofNullable(couponMap.get(couponIssue.getCouponId()))
			.orElseThrow(() -> new RuntimeException("유효하지 않은 쿠폰입니다."));
	}

	@Transactional
	public void issueCoupon(Long eventId, Long couponId, Long userId) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		checkEventAvailability(eventId, currentDateTime);
		Coupon coupon = getCoupon(eventId, couponId);
		checkDuplicateIssuanceStatus(userId, coupon, currentDateTime);
		increaseCouponQuantity(coupon);
		saveCouponIssue(userId, couponId, currentDateTime);
	}

	private void checkDuplicateIssuanceStatus(Long userId, Coupon coupon, LocalDateTime currentDateTime) {
		List<CouponIssue> couponIssueList = couponIssueRepository.findByUserIdAndCouponId(userId, coupon.getId());
		if (couponIssueList.size() > 1) {
			couponIssueList.stream()
				.filter(couponIssue -> !couponIssue.isUsed())
				.findAny()
				.ifPresent(couponIssue -> {
					throw new RuntimeException("사용하지 않은 동일한 쿠폰이 존재합니다.");
				});

			couponIssueList.stream()
				.filter(couponIssue -> couponIssue.isCouponIssuedToday(currentDateTime))
				.findAny()
				.ifPresent(couponIssue -> {
					throw new RuntimeException("하루에 한번만 쿠폰을 발급할 수 있습니다.");
				});
		} else if (couponIssueList.size() == 1 && !coupon.isDuplicateIssuable()) {
			throw new RuntimeException("해당 쿠폰은 중복 발급이 불가합니다.");
		}
	}

	private void checkEventAvailability(Long eventId, LocalDateTime currentDateTime) {
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("유효하지 않는 이벤트입니다."));
		event.checkAvailability(currentDateTime);
	}

	private Coupon getCoupon(Long eventId, Long couponId) {
		return couponRepository.findByIdAndEventId(eventId, couponId)
			.orElseThrow(() -> new RuntimeException("유효하지 않는 쿠폰입니다."));
	}

	private void increaseCouponQuantity(Coupon coupon) {
		coupon.increaseIssuedQuantity();
		couponRepository.updateIssuedQuantity(coupon);
	}

	private void saveCouponIssue(Long userId, Long couponId, LocalDateTime currentDateTime) {
		CouponIssue couponIssue = CouponIssue.create(userId, couponId, currentDateTime);
		couponIssueRepository.insertCouponIssue(couponIssue);
	}

	private Event getEvent(Map<Long, Event> eventMap, Coupon coupon) {
		return Optional.ofNullable(eventMap.get(coupon.getEventId()))
			.orElseThrow(() -> new RuntimeException("유효하지 않은 이벤트입니다."));
	}
}
