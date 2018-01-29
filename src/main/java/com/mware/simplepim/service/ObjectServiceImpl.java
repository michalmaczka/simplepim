package com.mware.simplepim.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mware.simplepim.domain.Object;
import com.mware.simplepim.repository.ObjectRepository;

@Component("objectService")
@Transactional
public class ObjectServiceImpl extends GenericServiceImpl<Object, ObjectRepository, Long> implements ObjectService {

	public ObjectServiceImpl(ObjectRepository repository) {
		super(repository);
	}
}
