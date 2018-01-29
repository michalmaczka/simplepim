package com.mware.simplepim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attribute_values")
public class AttributeValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String value;

	@ManyToOne
	@JoinColumn(name = "object_id")
	private Object object;

	@ManyToOne
	private Attribute attribute;

	@ManyToOne
	private AttributeGroup attributeGroup;

	public Attribute getAttribute() {
		return attribute;
	}

	public AttributeGroup getAttributeGroup() {
		return attributeGroup;
	}

	public Long getId() {
		return id;
	}

	public Object getObject() {
		return object;
	}

	public String getValue() {
		return value;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public void setAttributeGroup(AttributeGroup attributeGroup) {
		this.attributeGroup = attributeGroup;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
