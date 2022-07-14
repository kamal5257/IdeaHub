package com.idea.hub.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.idea.hub.dto.UserRegisterationDto;
import com.idea.hub.model.User;
import com.idea.hub.repository.UserRepository;

@Service
public class UserServices implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private RedissonClient client;
	
	
//	public UserServices (RedissonClient client) {
//		this.client = client;
//	}
	
	@Autowired
	private RedisTemplate<String, UserRegisterationDto> redisTemplate;
	
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
		
		try {
			redisTemplate.opsForHash().put("testQueue", registerationDto.getMobile(), registerationDto);
			System.out.println("$$$$$$$$$ "+redisTemplate.opsForHash().values("abc"));
		}catch(Exception e) {e.printStackTrace();}
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
