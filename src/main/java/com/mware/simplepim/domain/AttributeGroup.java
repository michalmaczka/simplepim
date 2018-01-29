package com.mware.simplepim.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "attribute_groups")
public class AttributeGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@ManyToMany(mappedBy = "attributeGroups")
	private List<ObjectType> objectTypes = new ArrayList<ObjectType>();

	public List<ObjectType> getObjectTypes() {
		return objectTypes;
	}

	public void setObjectTypes(List<ObjectType> objectTypes) {
		this.objectTypes = objectTypes;
	}

	@OneToMany(mappedBy = "attributeGroup", cascade = CascadeType.REMOVE)
	private List<AttributeValue> attributeValues;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "attribute_groups_attributes", joinColumns = {
			@JoinColumn(name = "attribute_group_id") }, inverseJoinColumns = { @JoinColumn(name = "attribute_id") })
	@OrderColumn(name = "sequence")
	@OnDelete(action = OnDeleteAction.CASCADE)
	public List<Attribute> attributes = new ArrayList<Attribute>();

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PreRemove
	private void removeAttributeGroupFromObjectType() {
		for (ObjectType objectType : getObjectTypes()) {
			objectType.getAttributeGroups().remove(this);
		}
	}
}
