package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Category;
import com.granduation.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{

	public List<Product> findByTopfeatured(Boolean top);

	public List<Product> findByCategory(Category cate, Pageable pageable);

	public List<Product> findByCategory(Category cate);

	@Query(value = "Select MAX(price) from product", nativeQuery = true)
	public int getMaxPrice();

	public Product findByName(String name);

	@Query(value = "SELECT * FROM product WHERE EXISTS(SELECT * From bill_info WHERE bill_info.product_id = product.id ORDER BY bill_info.quantity DESC LIMIT 3)", nativeQuery = true)
	public List<Product> findByBestSell();
}
