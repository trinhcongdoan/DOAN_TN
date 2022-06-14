package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Product_Comments;

@Service
public interface IFeedbackService {

	List<Product_Comments> findAll();

	void delete(int id);

	void save(Product_Comments comment);

}
