package com.idea.hub.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.idea.hub.model.User;


public interface UserRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
	public User getByEmail(String email);
	public User getById(long Id);
}
