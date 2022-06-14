package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Supplier;


@Service
public interface ISupplierService {

	public List<Supplier> findAll();

	public void update(Supplier supplier);

	public Supplier getById(int id);

	public void delete(int id);
}
