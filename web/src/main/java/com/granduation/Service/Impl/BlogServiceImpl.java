package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Blog;
import com.granduation.JPARepository.BlogRepository;
import com.granduation.Service.IBlogService;

@Service
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogRepository blogRepo;

	@Override
	public List<Blog> findAll() {
		return blogRepo.findAll();
	}

	@Override
	public Blog getById(int id) {
		return blogRepo.getById(id);
	}

	@Override
	public void update(Blog blog) {
		blogRepo.save(blog);
	}

	@Override
	public void delete(int id) {
		blogRepo.deleteById(id);
	}

	@Override
	public Page<Blog> findAll(Pageable pageRequest) {
		return blogRepo.findAll(pageRequest);
	}

}
