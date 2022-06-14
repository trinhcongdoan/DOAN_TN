package com.granduation.Controller.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Product;
import com.sun.istack.Nullable;

@Controller
public class UserShopController extends BaseController {
	@GetMapping("/san-pham")
	public String Shop(
			@Nullable @RequestParam(name = "nameProduct", defaultValue = "0") String nameProduct,
			@Nullable @RequestParam(name = "sort", defaultValue = "DESC") String sort,
			@Nullable @RequestParam(name = "start", defaultValue = "0") int filterStart,
			@Nullable @RequestParam(name = "end", defaultValue = "0") int filterEnd,
			@Nullable @RequestParam(name = "category", defaultValue = "0") int idCate,
			@Nullable @RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		model.addAttribute("productDiscount", discountService.findAll());
		model.addAttribute("categories", cateService.findAll());
		model.addAttribute("maxPrice", productService.getMaxPrice());
		
		int first = 0;
		int maxProduct = 9;
		if(!nameProduct.equalsIgnoreCase("0")) {
			Page<Product> lp = productService.filterByName(PageRequest.of(first, maxProduct), "name",
					nameProduct);
			model.addAttribute("products", lp);
			model.addAttribute("pAll", lp);
			return "pages/users/shop";
		}
		if (page > 1) {
			first = page - 1;
		}
		if (filterStart != 0 || filterEnd != 0) {
			Page<Product> lp = productService.filterByPrice(PageRequest.of(first, maxProduct), filterStart,
					filterEnd);
			model.addAttribute("products", lp);
			model.addAttribute("pAll", lp);
			return "pages/users/shop";
		}
		if (idCate != 0) {
			model.addAttribute("products",
					productService.findByCategory(cateService.getById(idCate), PageRequest.of(first, maxProduct)));
			model.addAttribute("pAll", productService.findByCategory(cateService.getById(idCate)));
			return "pages/users/shop";
		}
		model.addAttribute("pAll", productService.findAll());
		model.addAttribute("products", productService.findAll(PageRequest.of(first, maxProduct)));
		return "pages/users/shop";
	}
}
