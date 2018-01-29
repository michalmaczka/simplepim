package com.mware.simplepim.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mware.simplepim.domain.AttributeGroup;
import com.mware.simplepim.repository.AttributeGroupRepository;

@Component("attributeGroupService")
@Transactional
public class AttributeGroupServiceImpl extends GenericServiceImpl<AttributeGroup, AttributeGroupRepository, Long>
		implements AttributeGroupService {

	public AttributeGroupServiceImpl(AttributeGroupRepository repository) {
		super(repository);
	}

}
