package com.telekha.ttracker.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.AdminDto;
import com.telekha.ttracker.mapper.AdminMapper;
import com.telekha.ttracker.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends UserController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@RequestMapping(method=RequestMethod.GET)
	public AdminDto get(Principal principal) {
		return adminMapper.toAdminDto(adminService.findByMobileNo(principal.getName()));
	}
	
	
	
}
