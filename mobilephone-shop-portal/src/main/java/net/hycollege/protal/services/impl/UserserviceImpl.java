package net.hycollege.protal.services.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.Message;
import net.hycollege.mybatis.domain.User;
import net.hycollege.protal.services.UserService;

@Service
public class UserserviceImpl implements UserService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	private static final int thirtyMinutes = 1800;

	@Override
	public User select(String username, String password, String uuid) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("username", username);
			body.add("password", password);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			User user = restTemplate.postForObject("http://localhost:8082/user/login", request, User.class);
			user.setPassword(null);

			
			redisTemplate.opsForValue().set(uuid, objectMapper.writeValueAsString(user));
			redisTemplate.expire(uuid, thirtyMinutes, TimeUnit.SECONDS);
			if (user != null) {
				return user;
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public Message insert(User user) {
		return insertOrUpdate(user, "http://localhost:8082/user/register");
	}

	@Override
	public Message update(User user) {
		return insertOrUpdate(user, "http://localhost:8082/user/update");
	}

	private Message insertOrUpdate(User user, String urlInterface) {
		try {
			String jsonUser = new ObjectMapper().writeValueAsString(user);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON_UTF8);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("user", jsonUser);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject(urlInterface, request, Message.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

}
