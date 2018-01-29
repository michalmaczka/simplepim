package com.mware.simplepim.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mware.simplepim.domain.Attribute;
import com.mware.simplepim.domain.AttributeGroup;
import com.mware.simplepim.domain.AttributeValue;

public interface AttributeValueRepository extends PagingAndSortingRepository<AttributeValue, Long> {
	Optional<AttributeValue> findFirstByObjectAndAttributeGroupAndAttribute(Object object,
			AttributeGroup attributeGroup, Attribute attribute);
}
