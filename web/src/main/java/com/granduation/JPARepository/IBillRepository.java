package com.granduation.JPARepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Bill;
import com.granduation.Entity.Users;


@Repository
public interface IBillRepository extends JpaRepository<Bill, Integer> {

	@Query(value = "Select Max(id) from bill",nativeQuery = true)
	int getMaxId();

	List<Bill> findByStatus(String status);

	List<Bill> findByUser(Users user);

}
