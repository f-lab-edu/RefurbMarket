package com.refurbmarket.dto.furniture;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OptionGroupResponseDto {
	private String name;
	private List<OptionResponseDto> optionList;
}
