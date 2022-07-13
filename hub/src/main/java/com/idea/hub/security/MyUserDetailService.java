package com.idea.hub.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.idea.hub.model.User;
import com.idea.hub.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<User> user = repo.findByEmail(email);
		
		User u = user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new MyUserDetails(u);
	}

	
}
