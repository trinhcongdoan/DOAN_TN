package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Bill;
import com.granduation.Entity.Users;
import com.granduation.JPARepository.IBillRepository;
import com.granduation.Service.IBillService;

@Service
public class BillServiceImpl implements IBillService {
	
	@Autowired
	private IBillRepository billrepo;

	@Override
	public List<Bill> findAll() {
		return billrepo.findAll();
	}

	@Override
	public List<Bill> findByStatus(String status) {
		return billrepo.findByStatus(status);
	}

	@Override
	public Bill getById(int id) {
		return billrepo.getById(id);
	}

	@Override
	public int getMaxId() {
		return billrepo.getMaxId();
	}

	@Override
	public void save(Bill bill) {
		billrepo.save(bill);
	}

	@Override
	public void delete(int id) {
		billrepo.deleteById(id);
	}

	@Override
	public List<Bill> findByUser(Users byId) {
		return billrepo.findByUser(byId);
	}

}
