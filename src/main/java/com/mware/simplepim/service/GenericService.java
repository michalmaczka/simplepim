package com.mware.simplepim.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

public interface GenericService<T, PK extends Serializable> {
	void delete(T attribute);

	void deleteById(PK id);

	Iterable<T> findAll();

	Iterable<T> findAll(Pageable pageable);

	Optional<T> findById(PK id);

	T save(T attribute);
}
