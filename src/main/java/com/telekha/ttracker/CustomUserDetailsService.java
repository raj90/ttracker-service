package com.telekha.ttracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telekha.ttracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.telekha.ttracker.model.User user = userRepository.findByMobileNo(username);
		System.out.println(user.getMobileNo());
		System.out.println(user.getPassword());
		if(user == null) {
			throw new UsernameNotFoundException("could not find the user '"
	                  + username + "'");
		}
		else {
			User usr = new User(user.getMobileNo(), user.getPassword(), true, true, true, true,
	                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().toString()));

	        return usr;
		}
		
		
	}
}
