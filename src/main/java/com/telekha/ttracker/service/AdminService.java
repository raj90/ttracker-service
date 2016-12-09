package com.telekha.ttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telekha.ttracker.model.Admin;
import com.telekha.ttracker.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public Admin findByMobileNo(String mobileNo) {
		return adminRepository.findByMobileNo(mobileNo);
	}
}
