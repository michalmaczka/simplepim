package com.mware.simplepim.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mware.simplepim.domain.Attribute;
import com.mware.simplepim.repository.AttributeRepository;

@Component("attributeService")
@Transactional
public class AttributeServiceImpl extends GenericServiceImpl<Attribute, AttributeRepository, Long>
		implements AttributeService {

	public AttributeServiceImpl(AttributeRepository repository) {
		super(repository);
	}

}
