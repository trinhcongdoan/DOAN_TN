package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Product;
import com.granduation.Entity.Product_Comments;

@Repository
public interface ProductCommentRepository extends JpaRepository<Product_Comments, Integer>{
	List<Product_Comments> findByProduct(Product product);
}
