package com.mware.simplepim.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mware.simplepim.domain.ObjectType;
import com.mware.simplepim.repository.ObjectTypeRepository;

@Component("objectTypeService")
@Transactional
public class ObjectTypeServiceImpl extends GenericServiceImpl<ObjectType, ObjectTypeRepository, Long>
		implements ObjectTypeService {

	public ObjectTypeServiceImpl(ObjectTypeRepository repository) {
		super(repository);
	}

}
