package com.idea.hub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idea.hub.model.UserTokens;
import com.idea.hub.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public List<UserTokens> getAllUsers(){
		List<UserTokens> userTokens = new ArrayList<>();
		tokenRepository.findAll()
		.forEach(userTokens::add);
		return userTokens;
	}
	
	public UserTokens getByUsername(String username)
	{
		return tokenRepository.getByUsername(username);
	}
	
	public UserTokens getByToken(String token)
	{
		return tokenRepository.getByToken(token);
	}
}
