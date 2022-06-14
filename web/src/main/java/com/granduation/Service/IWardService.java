package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Ward;


@Service
public interface IWardService {

	public List<Ward> findByDistrictId(int id);
}
