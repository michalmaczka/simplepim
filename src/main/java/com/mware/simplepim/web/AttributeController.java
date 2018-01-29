package com.mware.simplepim.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mware.simplepim.domain.Attribute;
import com.mware.simplepim.service.AttributeService;

@Controller
public class AttributeController {
	@Autowired
	private AttributeService attributeService;

	@RequestMapping("attribute/delete/{id}")
	public String delete(@PathVariable Long id) {
		attributeService.deleteById(id);
		return "redirect:/attributes";
	}

	@RequestMapping(value = "attribute/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("attribute", attributeService.findById(id));
		return "attribute";
	}

	@RequestMapping(value = "/attributes", method = RequestMethod.GET)
	public String list(Model model, @PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("attributes", attributeService.findAll(pageable));
		return "attributes";
	}

	@RequestMapping("attribute/new")
	public String newAttribute(Model model) {
		model.addAttribute("attribute", new Attribute());

		return "attribute";
	}

	@RequestMapping(value = "attribute", method = RequestMethod.POST)
	public String save(Attribute attribute) {
		attributeService.save(attribute);
		return "redirect:/attribute/edit/" + attribute.getId();
	}
}
