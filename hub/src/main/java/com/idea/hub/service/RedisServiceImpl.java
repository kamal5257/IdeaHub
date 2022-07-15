//package com.idea.hub.service;
//
//import org.redisson.api.RMap;
//import org.redisson.api.RQueue;
//import org.redisson.api.RedissonClient;
//
//import com.idea.hub.dto.UserRegisterationDto;
//
//public class RedisServiceImpl {
//	
//	private RMap<String, UserRegisterationDto> testMap;
//	private RQueue<UserRegisterationDto> testQueue;
//	
//	private RedissonClient client;
//	
//	
//	public RedisServiceImpl (RedissonClient client) {
//		this.client = client;
//		testMap = client.getMap("testMap");
//		testQueue = client.getQueue("testQueue");
//	}
//	
//	public RedisServiceImpl () {
//		super();
//	}
//}
