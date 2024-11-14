package com.refurbmarket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.refurbmarket.domain.Event;
import com.refurbmarket.repository.mapper.EventMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisEventRepository {
	private final EventMapper eventMapper;

	public List<Event> findByIds(List<Long> idList) {
		return eventMapper.findByIds(idList);
	}

	public Optional<Event> findById(Long id) {
		return eventMapper.findById(id);
	}
}
