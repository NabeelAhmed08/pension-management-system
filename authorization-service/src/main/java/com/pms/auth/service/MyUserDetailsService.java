package com.pms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pms.auth.model.MyUserDetails;
import com.pms.auth.model.User;
import com.pms.auth.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	/** fetching user by username, if user is null the throw exception, otherwise
	 * return user
	 */
	
@Override
public UserDetails loadUserByUsername(String username) {
	User user =  userRepo.findByUsername(username);

	if (user == null) {
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	
	log.info("User with username "+username +" found");
	return  new MyUserDetails(user);
}
}
