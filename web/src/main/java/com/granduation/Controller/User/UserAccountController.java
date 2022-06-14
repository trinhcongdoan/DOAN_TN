package com.granduation.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {

	@GetMapping(value = "/tai-khoan")
	public String Account() {
		return "pages/users/my-account";
	}
}
