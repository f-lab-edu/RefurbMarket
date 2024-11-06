package com.refurbmarket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OptionResponseDto {
	private String name;
	private int additionalPrice;
}
