package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Discount;

@Service
public interface IDiscountService {

	public List<Discount> findAll();
	public Discount getById(int id);
	public void update(Discount discount);
	public void delete(int id);
	
}
