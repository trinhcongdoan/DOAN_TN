package com.granduation.Controller.User;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.granduation.Controller.BaseController;
import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;

@Controller
public class UserCartController extends BaseController{
	
	@GetMapping("/gio-hang")
	public String Cart(Model model,Principal principal) {
		model.addAttribute("categories", cateService.findAll());
		System.out.println("name" + principal.getName());
		Users user = userService.findByUserName(principal.getName());
		if(user == null) {
		}
		List<Cart> listcart = cartService.findByUser(user);
		model.addAttribute("listcart", listcart);
		Double total=0.0;
		for (Cart cart : listcart) {
			if(cart.getProduct().getPrice_discount()>0)
				total+=cart.getQuantity()*cart.getProduct().getPrice_discount();
			else
				total+=cart.getQuantity()*cart.getProduct().getPrice();
		}
		model.addAttribute("total",total);
		return "pages/users/cart";
	}
	
	@PostMapping("/api/cart-item")
	@ResponseBody
	public String CartItem(Principal principal) {
		if(principal == null) {
			return "";
		}
		Users user = userService.findByUserName(principal.getName());
		List<Cart> l_cart = cartService.findByUser(user);
		int cart_quatity = 0;
		double cart_total = 0.0;
		for (Cart cart : l_cart) {
			cart_quatity += cart.getQuantity();
			if(cart.getProduct().getPrice_discount()>0)
				cart_total += cart.getQuantity()*cart.getProduct().getPrice_discount();
			else
				cart_total += cart.getQuantity()*cart.getProduct().getPrice();
		}
		Map obj = new HashMap();
		obj.put("cart_quantity", cart_quatity);
		obj.put("cart_total", cart_total);
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PostMapping("/add-to-cart")
	@ResponseBody
	public String AddToCart(@RequestParam("productid") int productid,Principal principal) {
		if(principal.getName() == null) {
			return "Chưa đăng nhập";
		}
		Users user = userService.findByUserName(principal.getName());
		Product product = productService.getById(productid);
		cartService.addToCart(user, product);
		List<Cart> l_cart = cartService.findByUser(user);
		int cart_quatity = 0;
		double cart_total = 0.0;
		for (Cart cart : l_cart) {
			cart_quatity += cart.getQuantity();
			cart_total += cart.getQuantity()*cart.getProduct().getPrice();
		}
		Map obj = new HashMap();
		obj.put("cart_quantity", cart_quatity);
		obj.put("cart_total", cart_total);
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PostMapping("/update-cart")
	@ResponseBody
	public String UpdateToCart(@RequestParam("productid") int productid,@RequestParam("quantity") int quantity,Principal principal) {
		if(principal.getName() == null) {
			return "Chưa đăng nhập";
		}
		Users user = userService.findByUserName(principal.getName());
		Product product = productService.getById(productid);
		cartService.updateCart(user, product, quantity);
		List<Cart> l_cart = cartService.findByUser(user);
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(l_cart);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PostMapping("/delete-cart")
	@ResponseBody
	public String DeleteToCart(@RequestParam("productid") int productid,Principal principal) {
		if(principal.getName() == null) {
			return "Chưa đăng nhập";
		}
		Users user = userService.findByUserName(principal.getName());
		Product product = productService.getById(productid);
		cartService.deleteProductinCart(user, product);
		List<Cart> l_cart = cartService.findByUser(user);
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(l_cart);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
