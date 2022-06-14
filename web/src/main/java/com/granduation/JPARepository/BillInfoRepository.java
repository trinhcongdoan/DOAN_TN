package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.BillInfo;


@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, Integer> {

}
