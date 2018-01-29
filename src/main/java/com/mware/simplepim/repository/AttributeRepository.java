package com.mware.simplepim.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mware.simplepim.domain.Attribute;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long> {

}
