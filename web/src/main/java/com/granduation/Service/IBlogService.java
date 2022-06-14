package com.granduation.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Blog;

@Service
public interface IBlogService {

	public List<Blog> findAll();
	public Blog getById(int id);
	public void update(Blog blog);
	public void delete(int id);
	public Page<Blog> findAll(Pageable pageRequest);
	
}
