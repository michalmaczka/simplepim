package com.mware.simplepim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mware.simplepim.domain.ObjectType;
import com.mware.simplepim.service.AttributeGroupService;
import com.mware.simplepim.service.ObjectTypeService;
import com.mware.simplepim.web.exception.ObjectTypeNotFoundException;

@Controller
public class ObjectTypeController {
	@Autowired
	private ObjectTypeService objectTypeService;

	@Autowired
	private AttributeGroupService attributeGroupService;

	@RequestMapping("object-type/delete/{id}")
	public String delete(@PathVariable Long id) {
		objectTypeService.deleteById(id);
		return "redirect:/object-types";
	}

	@RequestMapping(value = "object-type/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		ObjectType objectType = objectTypeService.findById(id).orElseThrow(ObjectTypeNotFoundException::new);

		model.addAttribute("objectType", objectType);
		model.addAttribute("allAttributeGroups", attributeGroupService.findAll());
		return "object-type";
	}

	@RequestMapping(value = "/object-types", method = RequestMethod.GET)
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("objectTypes", objectTypeService.findAll(pageable));
		return "object-types";
	}

	@RequestMapping("object-type/new")
	public String newProduct(Model model) {
		model.addAttribute("objectType", new ObjectType());
		return "object-type";
	}

	@RequestMapping(value = "object-type", method = RequestMethod.POST)
	public String save(ObjectType objectType) {
		objectTypeService.save(objectType);
		return "redirect:/object-type/edit/" + objectType.getId();
	}
}
