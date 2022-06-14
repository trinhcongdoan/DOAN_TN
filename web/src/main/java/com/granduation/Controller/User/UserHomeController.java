package com.granduation.Controller.User;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Blog;

@Controller
@RequestMapping("/")
public class UserHomeController extends BaseController {
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("categories", cateService.findAll());
		model.addAttribute("topfeatured", productService.findByTop());
		model.addAttribute("bestseller", productService.findByBestSell());
		List<Blog> listBlog = blogService.findAll();
		if(!listBlog.isEmpty() && listBlog.size() > 3) {
			listBlog = listBlog.subList(0, 3);
		}
		model.addAttribute("listpost", listBlog);
		return "pages/users/index";
	}
}
