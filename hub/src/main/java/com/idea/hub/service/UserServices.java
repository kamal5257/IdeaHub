package com.idea.hub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idea.hub.dto.UserRegisterationDto;
import com.idea.hub.model.User;
import com.idea.hub.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		userRepository.findAll()
		.forEach(users::add);
		return users;
	}

	public User save(UserRegisterationDto registerationDto)
	{
		User user = new User(
				registerationDto.getName(),
				registerationDto.getPassword(),
				registerationDto.getEmail(),
				registerationDto.getDOB(),
				registerationDto.getMobile(),
				registerationDto.getRoles());
		
		return userRepository.save(user);
	}
	
	//public Optional<User> getUser(long Id){
		//return userRepository.findById(Id);
	//}
	
	public Optional<User> getUserById(long Id) {
		Optional<User> user=null;
		try {
			user = userRepository.findById(Id);
		}catch(Exception e) {e.printStackTrace();}
		
		return user;
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> getByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> getById(long Id){
		return userRepository.findById(Id);
	}
	
	
	public List<User> updateUser(User user){
		userRepository.save(user);
		return (List<User>) userRepository.findAll();
	}
	
	

	public List<User> deleteEmployee(long Id) {
		userRepository.deleteById(Id);
		return (List<User>) userRepository.findAll();
	}

	
}
