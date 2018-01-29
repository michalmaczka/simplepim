package com.mware.simplepim.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ObjectType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "object_types_attribute_groups", joinColumns = {
			@JoinColumn(name = "object_type_id") }, inverseJoinColumns = { @JoinColumn(name = "attribute_group_id") })
	@OrderColumn(name = "sequence")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<AttributeGroup> attributeGroups = new ArrayList<AttributeGroup>();

	public List<AttributeGroup> getAttributeGroups() {
		return attributeGroups;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAttributeGroups(List<AttributeGroup> attributeGroups) {
		this.attributeGroups = attributeGroups;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
