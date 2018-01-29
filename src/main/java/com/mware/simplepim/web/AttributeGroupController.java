package com.mware.simplepim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mware.simplepim.domain.AttributeGroup;
import com.mware.simplepim.service.AttributeGroupService;
import com.mware.simplepim.service.AttributeService;
import com.mware.simplepim.web.exception.AttributeGroupNotFoundException;

@Controller
public class AttributeGroupController {
	@Autowired
	private AttributeGroupService attributeGroupService;

	@Autowired
	private AttributeService attributeService;

	@RequestMapping("attribute-group/delete/{id}")
	public String delete(@PathVariable Long id) {
		attributeGroupService.deleteById(id);
		return "redirect:/attribute-groups";
	}

	@RequestMapping(value = "attribute-group/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		AttributeGroup attributeGroup = attributeGroupService.findById(id)
				.orElseThrow(AttributeGroupNotFoundException::new);

		model.addAttribute("attributeGroup", attributeGroup);
		model.addAttribute("allAttributes", attributeService.findAll());

		return "attribute-group";
	}

	@RequestMapping(value = "/attribute-groups", method = RequestMethod.GET)
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("attributeGroups", attributeGroupService.findAll(pageable));
		return "attribute-groups";
	}

	@RequestMapping("attribute-group/new")
	public String newProduct(Model model) {
		model.addAttribute("attributeGroup", new AttributeGroup());
		return "attribute-group";
	}

	@RequestMapping(value = "attribute-group", method = RequestMethod.POST)
	public String save(AttributeGroup attributeGroup) {
		attributeGroupService.save(attributeGroup);
		return "redirect:/attribute-group/edit/" + attributeGroup.getId();
	}
}
