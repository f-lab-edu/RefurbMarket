package com.refurbmarket.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Event {
	private Long id;
	private String name;
	private LocalDateTime startDate; // 이벤트 시작 날짜
	private LocalDateTime endDate; // 이벤트 종료 날짜
	private LocalTime dailyIssueStartTime; // 쿠폰 발급 시작 날짜
	private LocalTime dailyIssueEndTime; // 쿠폰 발급 종료 날짜

	private boolean availableIssueDate(LocalDateTime currentDateTime) {
		if (startDate == null || endDate == null)
			return false;
		return currentDateTime.isAfter(startDate) && currentDateTime.isBefore(endDate);
	}

	private boolean availableIssueTime(LocalDateTime currentDateTime) {
		if (dailyIssueStartTime == null || dailyIssueEndTime == null)
			return false;
		LocalTime currentTime = currentDateTime.toLocalTime();
		return currentTime.isAfter(dailyIssueStartTime) && currentTime.isBefore(dailyIssueEndTime);
	}

	public void checkAvailability(LocalDateTime currentDateTime) {
		if (!availableIssueDate(currentDateTime))
			throw new RuntimeException("이벤트 기간이 아닙니다.");
		if (!availableIssueTime(currentDateTime))
			throw new RuntimeException("쿠폰 발급 시간 아닙니다.");
	}
}
