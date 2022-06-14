package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

}
