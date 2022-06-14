package com.granduation.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Category;
import com.granduation.Entity.Product;


@Service
public interface IProductService {

	public Page<Product> findAll(Pageable pageRequest);
	public List<Product> findAll();
	public Product getById(int id);
	public void update(Product product);
	public void delete(int id);
	public Product getByName(String name);
	public List<Product> findByTop();
	public List<Product> findByBestSell();
	public List<Product> findByCategory(Category cate, Pageable pageable);
	public List<Product> findByCategory(Category byId);
	
	public Page<Product> filterByPrice(Pageable pageable,double start, double end);
	public int getMaxPrice();
	public Page<Product> filterByName(Pageable pageable, String string, String nameProduct);
	
}
