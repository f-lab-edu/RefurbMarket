package com.refurbmarket.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.refurbmarket.domain.Event;

@Mapper
public interface EventMapper {
	List<Event> findByIds(List<Long> idList);

	Optional<Event> findById(Long id);
}
