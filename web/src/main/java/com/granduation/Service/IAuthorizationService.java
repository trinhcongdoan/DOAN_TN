package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Authorization;

@Service
public interface IAuthorizationService {

	public List<Authorization> findAllAuth();

	public Authorization getByName(String name);

}
