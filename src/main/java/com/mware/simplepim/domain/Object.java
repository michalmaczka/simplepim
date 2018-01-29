package com.mware.simplepim.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "objects")
public class Object {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "object_type_id")
	@NotNull(message = "Object type is required")
	private ObjectType objectType;

	@OneToMany(mappedBy = "object", cascade = CascadeType.ALL)
	private List<AttributeValue> attributeValues = new ArrayList();

	@Column
	@CreationTimestamp
	private LocalDateTime created;

	@Column
	@UpdateTimestamp
	private LocalDateTime updated;

	public AttributeValue addAttributeValue(AttributeGroup attributeGroup, Attribute attribute) {
		AttributeValue attributeValue = new AttributeValue();
		attributeValue.setAttribute(attribute);
		attributeValue.setAttributeGroup(attributeGroup);
		attributeValue.setObject(this);
		getAttributeValues().add(attributeValue);

		return attributeValue;
	}

	public List<Attribute> getAllAttributes() {
		List<Attribute> allAttributes = new ArrayList<Attribute>();

		for (AttributeGroup attributeGroup : getObjectType().getAttributeGroups()) {
			for (Attribute attribute : attributeGroup.getAttributes()) {
				allAttributes.add(attribute);
			}
		}

		return allAttributes;
	}

	public Map<AttributeGroup, List<Attribute>> getAllAttributesByGroup() {
		Map<AttributeGroup, List<Attribute>> allAttributes = new HashMap<AttributeGroup, List<Attribute>>();

		for (AttributeGroup attributeGroup : getObjectType().getAttributeGroups()) {
			allAttributes.put(attributeGroup, attributeGroup.getAttributes());
		}

		return allAttributes;
	}

	public Optional<AttributeValue> getAttributeValue(AttributeGroup attributeGroup, Attribute attribute) {
		for (AttributeValue attributeValue : getAttributeValues()) {
			if (attributeGroup.equals(attributeValue.getAttributeGroup())
					&& attribute.equals(attributeValue.getAttribute())) {
				return Optional.of(attributeValue);
			}
		}

		return Optional.empty();
	}

	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void optimizeAttributes() {
		// TO DO
	}

	public void prepareAttributeValuesFields() {
		List<AttributeValue> newAttributeValues = new ArrayList<AttributeValue>();

		for (AttributeGroup attributeGroup : getObjectType().getAttributeGroups()) {
			for (Attribute attribute : attributeGroup.getAttributes()) {
				Optional<AttributeValue> attributeValue = getAttributeValue(attributeGroup, attribute);
				if (attributeValue.isPresent()) {
					newAttributeValues.add(attributeValue.get());
				}
				if (getAttributeValue(attributeGroup, attribute).isPresent() == false) {
					newAttributeValues.add(addAttributeValue(attributeGroup, attribute));
				}
			}
		}

		setAttributeValues(newAttributeValues);
	}

	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
}
