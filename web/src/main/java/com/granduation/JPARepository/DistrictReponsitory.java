package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.District;


@Repository
public interface DistrictReponsitory extends JpaRepository<District, Integer> {

	@Query(value = "Select * from district where _province_id = ?1",nativeQuery = true)
	public List<District> findByProvinceId(int id);
}
