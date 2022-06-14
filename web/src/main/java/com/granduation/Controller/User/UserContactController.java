package com.granduation.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserContactController {

	@GetMapping(value = "/lien-he")
	public String Contact() {
		return "pages/users/contact";
	}
}
