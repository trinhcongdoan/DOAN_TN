package com.granduation.Controller.Admin;

import java.util.ArrayList;
import java.util.List;

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
import com.granduation.Entity.Product;
import com.granduation.Entity.Product_Images;
import com.granduation.Utility.UploadImage;
import com.sun.istack.Nullable;

@Controller
public class AdminProductController extends BaseController {

	@GetMapping(value = "/admin-san-pham")
	public String Product(Model model) {
		model.addAttribute("products", productService.findAll());
		model.addAttribute("product", new Product());
		model.addAttribute("categories", cateService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		return "pages/admin/product";
	}

	@GetMapping(value = "/admin-san-pham-edit")
	public String editProduct(Model model, @Nullable @RequestParam(name = "id", defaultValue = "-1") int id) {
		model.addAttribute("categories", cateService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		if (id != -1) {
			model.addAttribute("product", productService.getById(id));
		} else {
			model.addAttribute("product", new Product());
		}
		return "pages/admin/edit-product";
	}

	@PostMapping(value = "/admin-san-pham-edit")
	public String editProductPost(Model model,@Valid Product product, BindingResult result,
			@RequestParam("imageUpload") MultipartFile[] file, @RequestParam("imageUploads") MultipartFile[] files) {
		if (result.hasErrors()) {
			model.addAttribute("categories", cateService.findAll());
			model.addAttribute("suppliers", supplierService.findAll());
			return "pages/admin/edit-product";
		}
		for (MultipartFile f : file) {
			if (f.getOriginalFilename() != "")
				product.setPimage(f.getOriginalFilename());
		}
		List<Product_Images> listPImg = new ArrayList<>();
		for (MultipartFile f : files) {
			if (f.getOriginalFilename() != "")
				listPImg.add(new Product_Images(f.getOriginalFilename(), product));
		}
		UploadImage.UploadFile(file);
		UploadImage.UploadFile(files);
		if (listPImg.size() > 0)
			product.setProduct_images(listPImg);
		productService.update(product);
		return "redirect:/admin-san-pham";
	}

	@GetMapping(value = "/admin-san-pham-xoa")
	public String Delete(@RequestParam("id") int id) {
		productService.delete(id);
		return "redirect:/admin-san-pham";
	}
}
