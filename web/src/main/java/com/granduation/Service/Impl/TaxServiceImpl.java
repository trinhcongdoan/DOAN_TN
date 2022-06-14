package com.granduation.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Tax;
import com.granduation.JPARepository.TaxRepository;
import com.granduation.Service.ITaxService;

@Service
public class TaxServiceImpl implements ITaxService {
	@Autowired
	private TaxRepository taxRepo;

	@Override
	public Tax findByType(String type) {
		return taxRepo.findByType(type);
	}

}
