package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Discount;


@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{

}
