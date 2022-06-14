package com.granduation.Controller.Admin;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Category;
import com.granduation.Utility.UploadImage;
import com.sun.istack.Nullable;

@Controller
public class AdminCategoryController extends BaseController {


	@GetMapping(value = "/admin-loai-hang")
	public String Category(Model model) {
		model.addAttribute("categories", cateService.findAll());
		return "pages/admin/category";
	}

	@GetMapping(value = "/admin-loai-hang-edit")
	public String createCategory(@Nullable @RequestParam(name = "id", defaultValue = "-1") int id, Model model) {
		if(id != -1) {
			model.addAttribute("category", cateService.getById(id));
		}else {
			model.addAttribute("category", new Category());
		}
		return "pages/admin/edit-category";
	}

	@PostMapping(value = "/admin-loai-hang-edit")
	public String editCreatePost(@Valid Category category,BindingResult result,
			@RequestParam("imageUpload") MultipartFile[] files) {
		if (result.hasErrors()) {
			return "pages/admin/edit-category";
		}
		for (MultipartFile file : files) {
			if (file.getOriginalFilename() != "")
				category.setImage(file.getOriginalFilename());
		}
		String t = UploadImage.UploadFile(files);
		cateService.update(category);
		return "redirect:/admin-loai-hang";
	}

	@GetMapping(value = "/admin-loai-hang-xoa")
	public String Delete(@RequestParam("id") int id) {
		cateService.delete(id);
		return "redirect:/admin-loai-hang";
	}
}
