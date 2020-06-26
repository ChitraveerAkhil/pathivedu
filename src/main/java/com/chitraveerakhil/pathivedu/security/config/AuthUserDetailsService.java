package com.chitraveerakhil.pathivedu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.security.model.AuthUserDetails;
import com.chitraveerakhil.pathivedu.service.UserService;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = service.findByUsername(username);
		if (user == null) {
			new UsernameNotFoundException("User Not Found with username: " + username);

		}
		return AuthUserDetails.build(user);
	}

}
