package com.telekha.ttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telekha.ttracker.model.Driver;
import com.telekha.ttracker.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	public Driver findByMobileNo(String mobileNo) {
		return driverRepository.findByMobileNo(mobileNo);
	}
}
