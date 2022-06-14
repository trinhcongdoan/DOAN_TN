package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;
import com.granduation.JPARepository.CartRepository;
import com.granduation.Service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<Cart> findByUser(Users user) {
		return cartRepo.findByUser(user);
	}

	@Override
	public void addToCart(Users user, Product product) {
		// nếu tồn tại product trong giỏ hàng thì gọi update
		Cart cart = cartRepo.findByUserAndProduct(user, product);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(1);
			cartRepo.save(cart);
			return;
		}
		// nếu sản phẩm chưa có trong giỏ hàng thì thêm mới
		updateCart(user, product, cart.getQuantity() + 1);
		return;
	}

	@Override
	public void updateCart(Users user, Product product, int quantity) {
		Cart cart = cartRepo.findByUserAndProduct(user, product);
		if (cart == null) {
			return;
		}
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		return;
	}

	@Override
	public void deleteProductinCart(Users user, Product product) {
		Cart cart = cartRepo.findByUserAndProduct(user, product);
		if (cart == null) {
			return;
		}
		cartRepo.delete(cart);
		return;

	}

}
