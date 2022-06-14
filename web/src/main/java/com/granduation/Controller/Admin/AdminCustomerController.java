package com.granduation.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;

@Controller
public class AdminCustomerController extends BaseController {

	@GetMapping(value="/admin-khach-hang")
	public String Customer(Model model) {
		model.addAttribute("customers", userService.findAll());
		return "pages/admin/customer";
	}
	
	@GetMapping(value="/admin-khach-hang-mua-hang")
	public String detailsCustomer(@RequestParam(name ="id") int id, Model model) {
		model.addAttribute("transactions",billService.findByUser(userService.getById(id)));
		return "pages/admin/details-buy-customer";
	}
}
