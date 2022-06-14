package com.granduation.Controller.Admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Blog;
import com.granduation.Utility.FileWord;
import com.granduation.Utility.UploadImage;
import com.sun.istack.Nullable;

@Controller
public class AdminBlogPostController extends BaseController {

	@GetMapping(value = "/admin-blog-post")
	public String BlogPost(Model model) {
		model.addAttribute("listpost", blogService.findAll());

		return "pages/admin/blog-post";
	}

	@GetMapping(value = "/admin-blog-post-edit")
	public String editBlogPost(Model model, @Nullable @RequestParam(name = "id", defaultValue = "-1") int id) {
		String content = null;
		if (id != -1) {
			Blog blg = blogService.getById(id);
			try {
				content = FileWord.ReadFile(blg.getContent());
			} catch (IOException e) {
				e.printStackTrace();
			}
			blg.setContent(content);
			model.addAttribute("blog", blg);
		} else {
			model.addAttribute("blog", new Blog());
		}
		model.addAttribute("products", productService.findAll());
		return "pages/admin/edit-blog-post";
	}

	@PostMapping(value = "/admin-blog-post-edit")
	public String editBlogPost(Model model, @Valid Blog blog, BindingResult result, @RequestParam("imageUpload") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("products", productService.findAll());
			return "pages/admin/edit-blog-post";
		}
		for (MultipartFile file : files) {
			if (file.getOriginalFilename() != "")
				blog.setImage(file.getOriginalFilename());
		}
		UploadImage.UploadFile(files);
		try {
			FileWord.WrirteFile(blog.getSlug(), blog.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		blog.setContent(blog.getSlug());
		blogService.update(blog);
		return "redirect:/admin-blog-post";
	}

	@GetMapping(value = "/admin-blog-post-xoa")
	public String delete(@RequestParam("id") int id) {
		Blog blg = blogService.getById(id);
		try {
			FileWord.WrirteFile(blg.getSlug(), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		blogService.delete(id);
		return "redirect:/admin-blog-post";
	}
}
