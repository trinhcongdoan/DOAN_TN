package com.granduation.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWishListController {

	@GetMapping(value = "/yeu-thich")
	public String WishList() {
		return "pages/users/wishlist";
	}
}
