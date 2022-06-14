package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;


@Service
public interface ICartService {
	
	public List<Cart> findByUser(Users user);
	public void addToCart(Users user,Product product);
	public void updateCart(Users user, Product product, int quantity);
	public void deleteProductinCart(Users user,Product product);
	
}
