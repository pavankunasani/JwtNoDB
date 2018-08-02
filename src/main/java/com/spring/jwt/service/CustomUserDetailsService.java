package com.spring.jwt.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.jwt.model.ApplicationUser;

import static java.util.Collections.emptyList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	
	// second
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		ApplicationUser appUser = loadAppUser(username);
		// return new User(appUser.getUsername(), appUser.getPassword(),
		// emptyList());
		return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	public ApplicationUser loadAppUser(String username) {
		System.out.println("========================================");
		System.out.println("login process");
		return new ApplicationUser("pavan", "pass");
	}
}
