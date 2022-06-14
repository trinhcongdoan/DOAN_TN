package com.granduation.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.granduation.Controller.BaseController;

@Controller
public class AdminLoginController extends BaseController{

	@GetMapping("/admin-dang-nhap")
	public String Login() {
		return "pages/admin/login";
	}
	
}
