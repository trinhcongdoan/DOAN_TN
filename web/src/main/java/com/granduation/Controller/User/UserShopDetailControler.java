package com.granduation.Controller.User;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Product;
import com.granduation.Entity.Product_Comments;
import com.granduation.Entity.Users;
import com.sun.istack.Nullable;

@Controller
public class UserShopDetailControler extends BaseController {

	@GetMapping(value = "/chi-tiet-san-pham")
	public String ShopDetail(Principal principal, Model model, @Nullable @RequestParam(name = "productid", defaultValue = "0") int id, @Nullable @RequestParam(name = "categoryid", defaultValue = "1") int cateid) {
		Product product = productService.getById(id);
		product.setView(product.getView() + 1);
		productService.update(product);
		model.addAttribute("productRelate", productService.findByCategory(cateService.getById(product.getCategory().getId())));
		model.addAttribute("product", product);
		Double rank = 0.0;
		for (Product_Comments pComment : product.getProduct_comment()) {
			rank+=pComment.getRank();
		}
		model.addAttribute("rank",rank/product.getProduct_comment().size());
		if(principal != null && userService.findByUserName(principal.getName())!= null) {
			Users user = userService.findByUserName(principal.getName());
			Product_Comments comment = new Product_Comments();
			comment.setProduct(product);
			comment.setEmail(user.getEmail());
			comment.setName(user.getFirstname()+" "+user.getLastname());
			model.addAttribute("comment", comment);
		}
		return "pages/users/shop-details";
	}

	@PostMapping(value = "/comment")
	public String Comment(@ModelAttribute Product_Comments comment) {
		feedbackService.save(comment);
		return "redirect:/chi-tiet-san-pham?productid="+comment.getProduct().getId();
	}
}
