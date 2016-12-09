package com.telekha.ttracker.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.telekha.ttracker.dto.UserDetailsChangeDto;
import com.telekha.ttracker.service.UserService;

public abstract class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST)
	public void updateUserDetails(@RequestBody UserDetailsChangeDto userDetailsChangeDto, Principal principal) {
		userService.changeUserDetails(userDetailsChangeDto, principal.getName());
	}
}
