package com.training.demoo.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.training.demoo.entity.EmployeeClassEntity;
import com.training.demoo.service.EmployeeClassService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeClassService service;

	@RequestMapping("/")
	public String getAllEmployees(Model model) {
		List<EmployeeClassEntity> list = service.getAllEmployees();
		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping(path = { "/add", "/edit/{id}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws Exception {
		if (id.isPresent()) {
			EmployeeClassEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeClassEntity());
		}
		return "add-edit-employee";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createEmployee(@Valid @ModelAttribute("employee") EmployeeClassEntity employee,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "add-edit-employee";
		} else {
			service.createOrUpdateEmployee(employee);
			return "redirect:/";
		}
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") Long id) throws Exception {
		service.deleteEmployeeById(id);
		return "redirect:/";
	}
}
