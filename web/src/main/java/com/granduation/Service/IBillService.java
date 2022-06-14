package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Bill;
import com.granduation.Entity.Users;

@Service
public interface IBillService {
	
	public List<Bill> findAll();
	public List<Bill> findByStatus(String status);

	public Bill getById(int id);
	
	public int getMaxId();

	public void save(Bill bill);

	public void delete(int id);
	public List<Bill> findByUser(Users byId);
}
