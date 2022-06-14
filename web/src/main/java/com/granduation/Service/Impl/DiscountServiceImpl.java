package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Discount;
import com.granduation.JPARepository.DiscountRepository;
import com.granduation.Service.IDiscountService;

@Service
public class DiscountServiceImpl implements IDiscountService {
	@Autowired
	private DiscountRepository discountRepo;

	@Override
	public List<Discount> findAll() {
		return discountRepo.findAll();
	}

	@Override
	public Discount getById(int id) {
		return discountRepo.getById(id);
	}

	@Override
	public void update(Discount discount) {
		discountRepo.save(discount);
	}

	@Override
	public void delete(int id) {
		discountRepo.deleteById(id);
	}

}
