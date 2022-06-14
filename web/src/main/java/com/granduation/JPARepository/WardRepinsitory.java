package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Ward;


@Repository
public interface WardRepinsitory extends JpaRepository<Ward, Integer> {

	@Query(value = "Select * from ward where _district_id = ?1",nativeQuery = true)
	public List<Ward> findByDistrictId(int id);
}
