//package com.idea.hub.config;
//
//import java.io.IOException;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import com.idea.hub.dto.UserRegisterationDto;
//import com.idea.hub.service.RedisServiceImpl;
//import com.idea.hub.service.UserServices;
//
////@EnableAutoConfiguration
//@Configuration
//public class RedisConfig {
//	
//	@Value("${redis.IP}")
//	private String redisIP;
//	
//	@Bean
//	RedissonClient redissonClient() throws IOException{
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://"+redisIP);
//		RedissonClient redisson = Redisson.create(config);
//		return redisson;
//	}
//	
//	@Bean
//	public RedisServiceImpl redisServiceImpl() throws IOException {
//		return new RedisServiceImpl(redissonClient());
//	}
//	
////	@Bean
////	public JedisConnectionFactory jedisConnectionFactory() {
////		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
////		redisStandaloneConfiguration.setHostName("localhost");
////		redisStandaloneConfiguration.setPort(6379);
//////		redisStandaloneConfiguration.setPassword("");
////		
////		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
////		return jedisConnectionFactory;
////	}
////	
////	@Bean
////	public RedisTemplate<String, UserRegisterationDto> redisTemplate() {
////		RedisTemplate<String, UserRegisterationDto> redisTemplate = new RedisTemplate<>();
////		redisTemplate.setConnectionFactory(jedisConnectionFactory());
////		redisTemplate.setKeySerializer(new StringRedisSerializer());
////		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
////		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
////		redisTemplate.setEnableTransactionSupport(true);
////		redisTemplate.afterPropertiesSet();
////		return redisTemplate;
////	}
//}
