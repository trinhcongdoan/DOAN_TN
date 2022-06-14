package com.granduation.Controller.Admin;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Users;
import com.sun.istack.Nullable;

@Controller
public class AdminInfoUserController extends BaseController {

	@GetMapping(value = "/admin-danh-sach-nguoi-dung")
	public String listUser(Model model) {
		model.addAttribute("listUser", userService.findAll());
		return "pages/admin/list-user";
	}

	@GetMapping(value = "/admin-thong-tin-su-dung")
	public String infoUser(Model model, Principal principal) {
		model.addAttribute("user", userService.findByUserName(principal.getName()));
		model.addAttribute("provinces", provinceService.findAllProvince());
		model.addAttribute("auths", authService.findAllAuth());
		return "pages/admin/info-user";
	}

	@PostMapping(value = "/admin-thong-tin-su-dung")
	public String infoUserPost(Principal principal,Model model,@Valid Users user, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("user", userService.findByUserName(principal.getName()));
			model.addAttribute("provinces", provinceService.findAllProvince());
			model.addAttribute("auths", authService.findAllAuth());
			return "pages/admin/info-user";
		}
		user.setEnable(true);
		userService.save(user);
		return "redirect:/admin-thong-tin-su-dung";
	}

	@GetMapping(value = "/admin-thay-doi-mat-khau")
	public String changePasswordUser(Model model,@Nullable @RequestParam(name = "error", defaultValue = "") String error) {
		if(!error.equals("")) {
			model.addAttribute("message","Mật khẩu k đúng!");
		}else {
			model.addAttribute("message","");
		}
		return "pages/admin/change-password";
	}

	@PostMapping(value = "/admin-thay-doi-mat-khau")
	public String changePost(@RequestParam("oldpassword") String oldpass, @RequestParam("newpassword") String newpass,
			Principal principal) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Users u = userService.findByUserName(principal.getName());
		if(!passwordEncoder.matches(oldpass, u.getPassword())) {
			return "redirect:/admin-thay-doi-mat-khau?error=" + "failed";
		}
		userService.updatePassword(u, newpass);
		return "redirect:/admin-thong-tin-su-dung";
	}
	
	@GetMapping(value ="/admin-danh-sach-nguoi-dung-xoa")
	public String delete(@RequestParam("id") int id) {
		userService.delete(id);
		return "redirect:/admin-danh-sach-nguoi-dung"; 
	}
}
