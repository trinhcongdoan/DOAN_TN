package com.granduation.Service;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Tax;


@Service
public interface ITaxService {

	public Tax findByType(String type);
}
