package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart> findByUser(Users user);

	public Cart findByUserAndProduct(Users user, Product product);

	@Query(value = "Select MAX(id) from cart", nativeQuery = true)
	public int getMaxId();
}
