package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Supplier;
import com.granduation.JPARepository.SupplierRepository;
import com.granduation.Service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public void update(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public Supplier getById(int id) {
		return supplierRepository.getById(id);
	}

	@Override
	public void delete(int id) {
		supplierRepository.deleteById(id);
	}

}
