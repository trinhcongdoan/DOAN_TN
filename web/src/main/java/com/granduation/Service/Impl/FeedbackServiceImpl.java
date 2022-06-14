package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Product_Comments;
import com.granduation.JPARepository.ProductCommentRepository;
import com.granduation.Service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private ProductCommentRepository feedbackRepo;

	@Override
	public List<Product_Comments> findAll() {
		return feedbackRepo.findAll();
	}

	@Override
	public void delete(int id) {
		feedbackRepo.deleteById(id);

	}

	@Override
	public void save(Product_Comments comment) {
		feedbackRepo.save(comment);
	}

}
