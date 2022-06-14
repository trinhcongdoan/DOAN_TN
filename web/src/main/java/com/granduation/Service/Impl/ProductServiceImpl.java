package com.granduation.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Category;
import com.granduation.Entity.Product;
import com.granduation.JPARepository.ProductRepository;
import com.granduation.Service.IProductService;
import com.granduation.Specification.ProductSpecification;
import com.granduation.Specification.SearchCriteria;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	@Override
	public Page<Product> findAll(Pageable pageRequest) {
		return productRepository.findAll(pageRequest);
	}

	@Override
	public List<Product> findAll() {
		List<Product> list = new ArrayList<Product>();
		for (Product product : productRepository.findAll()) {
			if(product.getQuantity()>0) {
				list.add(product);
			}
		}
		return list;
	}

	@Override
	public Product getById(int id) {
		return productRepository.getById(id);
	}

	@Override
	public void update(Product product) {
		if(product.getQuantity() < 0) {
			return;
		}
		productRepository.save(product);
	}

	@Override
	public void delete(int id) {
		productRepository.deleteById(id);		
	}

	@Override
	public Product getByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findByTop() {
		return productRepository.findByTopfeatured(true);
	}

	@Override
	public List<Product> findByBestSell() {
		return productRepository.findByBestSell();
	}


	@Override
	public List<Product> findByCategory(Category cate, Pageable pageable) {
		return productRepository.findByCategory(cate, pageable);
	}

	@Override
	public List<Product> findByCategory(Category byId) {
		return productRepository.findByCategory(byId);
	}

	@Override
	public Page<Product> filterByPrice(Pageable pageable, double start, double end) {
		ProductSpecification spec1 = new ProductSpecification(new SearchCriteria("price",">",start));
		ProductSpecification spec2 = new ProductSpecification(new SearchCriteria("price","<",end));
		Page<Product> list = productRepository.findAll(Specification.where(spec1).and(spec2),pageable);
		return list;
	}

	@Override
	public int getMaxPrice() {
		return productRepository.getMaxPrice();
	}

	@Override
	public Page<Product> filterByName(Pageable pageable, String string, String nameProduct) {
		ProductSpecification spec1 = new ProductSpecification(new SearchCriteria(string,":",nameProduct));
		Page<Product> list = productRepository.findAll(Specification.where(spec1),pageable);
		return list;
	}

}
