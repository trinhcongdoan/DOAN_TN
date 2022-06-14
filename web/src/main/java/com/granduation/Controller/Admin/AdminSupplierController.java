package com.granduation.Controller.Admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Supplier;
import com.sun.istack.Nullable;

@Controller
public class AdminSupplierController extends BaseController {

	@GetMapping(value = "/admin-nha-cung-cap")
	public String Supplier(Model model) {
		model.addAttribute("suppliers", supplierService.findAll());
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("provinces", provinceService.findAllProvince());
		return "pages/admin/supplier";
	}

	@GetMapping(value = "/admin-nha-cung-cap-edit")
	public String editSupplier(Model model, @Nullable @RequestParam(name = "id", defaultValue = "-1") int id) {
		if (id != -1) {
			model.addAttribute("supplier", supplierService.getById(id));
		} else {
			model.addAttribute("supplier", new Supplier());
		}
		model.addAttribute("provinces", provinceService.findAllProvince());
		return "pages/admin/edit-supplier";
	}

	@PostMapping(value = "/admin-nha-cung-cap-edit")
	public String editPostSupplier(@ModelAttribute @Valid Supplier supplier,BindingResult result,Model model) {
		if (result.hasErrors()) {
			model.addAttribute("provinces", provinceService.findAllProvince());
			return "pages/admin/edit-supplier";
		}
		supplierService.update(supplier);
		return "redirect:/admin-nha-cung-cap";
	}

	@GetMapping(value = "/admin-nha-cung-cap-xoa")
	public String Delete(@RequestParam("id") int id) {
		supplierService.delete(id);
		return "redirect:/admin-nha-cung-cap";
	}
}
