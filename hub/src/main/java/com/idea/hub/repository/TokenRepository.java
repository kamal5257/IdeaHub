package com.idea.hub.repository;

import org.springframework.data.repository.CrudRepository;

import com.idea.hub.model.UserTokens;




public interface TokenRepository extends CrudRepository<UserTokens, Long>{

	public UserTokens getByUsername(String username);
	public UserTokens getByToken(String token);
}
