package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Authorization;
import com.granduation.JPARepository.AuthorizationReponsitory;
import com.granduation.Service.IAuthorizationService;


@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

	@Autowired
	private AuthorizationReponsitory auth_repo;
	@Override
	public List<Authorization> findAllAuth() {
		return auth_repo.findAll();
	}
	@Override
	public Authorization getByName(String name) {
		return auth_repo.findByRole(name);
	}

}
