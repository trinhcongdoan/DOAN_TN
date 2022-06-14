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
import com.granduation.Entity.Discount;
import com.granduation.Entity.Product;
import com.sun.istack.Nullable;

@Controller
public class AdminDiscountController extends BaseController {

	@GetMapping(value = "/admin-khuyen-mai")
	public String Discount(Model model) {
		model.addAttribute("discounts", discountService.findAll());
		return "pages/admin/discount";
	}

	@GetMapping(value = "/admin-khuyen-mai-edit")
	public String editDiscount(Model model, @Nullable @RequestParam(name = "id", defaultValue = "-1") int id) {
		if (id != -1) {
			model.addAttribute("discount", discountService.getById(id));
		} else {
			model.addAttribute("discount", new Discount());
		}
		model.addAttribute("products", productService.findAll());
		return "pages/admin/edit-discount";
	}

	@PostMapping(value = "/admin-khuyen-mai-edit")
	public String editDiscountPost(Model model, @Valid @ModelAttribute Discount discount, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			return "pages/admin/edit-discount";
		}
		if (discount.getProduct() != null) {
			Product p = productService.getById(discount.getProduct().getId());
			p.setPrice_discount(p.getPrice() - discount.getDiscount() * 1.0 / 100 * p.getPrice());
			productService.update(p);
		}
		discountService.update(discount);
		return "redirect:/admin-khuyen-mai";
	}

	@GetMapping(value = "/admin-khuyen-mai-xoa")
	public String Delete(@RequestParam("id") int id) {
		Product product = discountService.getById(id).getProduct();
		product.setPrice_discount(0.0);
		productService.update(product);
		discountService.delete(id);
		return "redirect:/admin-khuyen-mai";
	}

}
