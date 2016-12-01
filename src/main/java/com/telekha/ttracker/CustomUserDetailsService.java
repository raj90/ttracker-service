package com.telekha.ttracker;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usr = new User("abcdefg", "123", true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SUPERADMIN"));

        return usr;
	}
}
