package com.granduation.Service;

import org.springframework.stereotype.Service;

import com.granduation.Entity.BillInfo;

@Service
public interface IBillInfoService {

	public void save(BillInfo b);
}
