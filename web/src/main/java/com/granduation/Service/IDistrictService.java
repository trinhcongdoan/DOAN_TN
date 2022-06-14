package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.District;


@Service
public interface IDistrictService {

	public List<District> findByProvinceId(int id);
}
