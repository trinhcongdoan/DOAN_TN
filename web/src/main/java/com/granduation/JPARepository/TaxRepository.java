package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {

	public Tax findByType(String type);
}
