package com.refurbmarket.dto.furniture;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OptionGroupResponseDto {
	private String name;
	private List<OptionResponseDto> optionList;
}
