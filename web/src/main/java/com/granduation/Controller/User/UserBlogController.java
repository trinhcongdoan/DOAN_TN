package com.granduation.Controller.User;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Blog;
import com.sun.istack.Nullable;

@Controller
public class UserBlogController extends BaseController {

	@GetMapping(value = "/blog")
	public String Blog(Model model,@Nullable @RequestParam(name = "page", defaultValue = "1") int page) {
		List<Blog> blogtop = blogService.findAll();
		if(!blogtop.isEmpty() && blogtop.size() > 3) {
			int n = ThreadLocalRandom.current().nextInt(0, blogtop.size() - 3 + 1);
			blogtop = blogtop.subList(n, n+3);
		}
		model.addAttribute("blogtop", blogtop);
		model.addAttribute("topfeatured", productService.findByTop());
		int first = 0;
		int maxBlog = 6;
		if (page > 1) {
			first = page-1;
		}
		model.addAttribute("listblog",blogService.findAll(PageRequest.of(first, maxBlog)));
		model.addAttribute("listblogAll",blogService.findAll());
		return "pages/users/blog";
	}
}
