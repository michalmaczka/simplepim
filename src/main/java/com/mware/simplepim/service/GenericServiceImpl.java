package com.mware.simplepim.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public class GenericServiceImpl<T, D extends PagingAndSortingRepository, ID extends Serializable>
		implements GenericService<T, ID> {

	protected PagingAndSortingRepository<T, ID> repository;

	@Autowired
	public GenericServiceImpl(D repository) {
		this.repository = repository;
	}

	@Override
	public void delete(T attribute) {
		repository.delete(attribute);
	}

	@Override
	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T save(T attribute) {
		return repository.save(attribute);
	}
}
