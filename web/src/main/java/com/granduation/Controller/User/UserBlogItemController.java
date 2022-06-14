package com.granduation.Controller.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Blog;
import com.granduation.Utility.FileWord;

@Controller
public class UserBlogItemController extends BaseController{

	@GetMapping(value="/bai-viet")
	public String BlogItem(Model model, @RequestParam(name = "id") int id) {
		List<Blog> blogtop = blogService.findAll();
		if(!blogtop.isEmpty() && blogtop.size() > 3) {
			int n = ThreadLocalRandom.current().nextInt(0, blogtop.size() - 3 + 1);
			blogtop = blogtop.subList(n, n+3);
		}
		if(!blogtop.isEmpty() && blogtop.size() > 3) {
			int n = ThreadLocalRandom.current().nextInt(0, blogtop.size() - 3 + 1);
			model.addAttribute("blogRelate", blogtop.subList(n, n+3));
		}
		model.addAttribute("blogtop", blogtop);
		model.addAttribute("topfeatured", productService.findByTop());
		String content = null;
		Blog blg = blogService.getById(id);
		try {
			content = FileWord.ReadFile(blg.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		blg.setContent(content);
		model.addAttribute("blogitem", blg);
		return "pages/users/blogItem";
	}
}
