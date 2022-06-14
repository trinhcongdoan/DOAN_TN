package com.granduation.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.BillInfo;
import com.granduation.JPARepository.BillInfoRepository;
import com.granduation.Service.IBillInfoService;

@Service
public class BillInfoServiceImpl implements IBillInfoService{

	@Autowired
	private BillInfoRepository billInforepo;
	@Override
	public void save(BillInfo b) {
		billInforepo.save(b);
	}

}
