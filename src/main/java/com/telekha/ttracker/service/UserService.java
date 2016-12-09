package com.telekha.ttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telekha.ttracker.dto.UserDetailsChangeDto;
import com.telekha.ttracker.model.User;
import com.telekha.ttracker.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void changeUserDetails(UserDetailsChangeDto userDetailsChangeDto, String mobileNo) {
		User user = userRepository.findByMobileNo(mobileNo);
		if(userDetailsChangeDto.getEmail() != null) {
			user.setEmailId(userDetailsChangeDto.getEmail());
		}
		if(userDetailsChangeDto.getName() != null) {
			user.setName(userDetailsChangeDto.getName());
		}
		if(userDetailsChangeDto.getPassword() != null) {
			user.setPassword(userDetailsChangeDto.getPassword());
		}
		userRepository.save(user);
	}
}
