package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Category;


@Service
public interface ICategoryService {

	public List<Category> findAll();
	public Category getById(int id);
	public void update(Category category);
	public void delete(int id);
}
