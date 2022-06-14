package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Authorization;


@Repository
public interface AuthorizationReponsitory extends JpaRepository<Authorization, Integer> {
	public Authorization findByRole(String role);
}
