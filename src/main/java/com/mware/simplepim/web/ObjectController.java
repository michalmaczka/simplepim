package com.mware.simplepim.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mware.simplepim.domain.Object;
import com.mware.simplepim.service.AttributeGroupService;
import com.mware.simplepim.service.ObjectService;
import com.mware.simplepim.service.ObjectTypeService;
import com.mware.simplepim.web.exception.ObjectNotFoundException;

@Controller
public class ObjectController {
	@Autowired
	private ObjectService objectService;

	@Autowired
	private AttributeGroupService attributeGroupService;

	@Autowired
	private ObjectTypeService objectTypeService;

	@RequestMapping("object/delete/{id}")
	public String delete(@PathVariable Long id) {
		objectService.deleteById(id);
		return "redirect:/";
	}

	@RequestMapping(value = "object/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Object object = objectService.findById(id).orElseThrow(ObjectNotFoundException::new);
		object.prepareAttributeValuesFields();

		model.addAttribute("object", object);
		return "object";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("objects", objectService.findAll(pageable));
		return "objects";
	}

	@RequestMapping("object/new")
	public String newProduct(Model model) {
		model.addAttribute("object", new Object());
		return "object";
	}

	@RequestMapping(value = "object", method = RequestMethod.POST)
	public String save(@Valid Object object, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "object";
		}

		objectService.save(object);
		return "redirect:/object/edit/" + object.getId();
	}

	@ModelAttribute("objectTypes")
	public void populateObjectTypes(@RequestParam(value = "objectTypes", required = false) String querier,
			Model model) {
		model.addAttribute("objectTypes", objectTypeService.findAll());
	}
}
