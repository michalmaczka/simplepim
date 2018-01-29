package com.mware.simplepim.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mware.simplepim.domain.Object;

public interface ObjectRepository extends PagingAndSortingRepository<Object, Long> {

}
