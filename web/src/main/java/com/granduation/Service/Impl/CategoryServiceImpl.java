package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Category;
import com.granduation.JPARepository.CategoryReponsitory;
import com.granduation.Service.ICategoryService;


@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private CategoryReponsitory cate_repo;
	@Override
	public List<Category> findAll() {
		return cate_repo.findAll();
	}

	@Override
	public Category getById(int id) {
		return cate_repo.getById(id);
	}

	@Override
	public void update(Category category) {
		cate_repo.save(category);
		
	}

	@Override
	public void delete(int id) {
		cate_repo.deleteById(id);
	}

}
