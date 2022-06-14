package com.granduation.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAboutController {

	@GetMapping(value = "/ve-shop")
	public String About() {
		return "pages/users/about";
	}
}
