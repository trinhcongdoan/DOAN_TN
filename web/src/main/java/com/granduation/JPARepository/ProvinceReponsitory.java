package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Province;


@Repository
public interface ProvinceReponsitory extends JpaRepository<Province, Integer> {

}
