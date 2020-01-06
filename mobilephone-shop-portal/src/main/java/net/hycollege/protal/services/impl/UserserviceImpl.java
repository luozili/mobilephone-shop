package net.hycollege.protal.services.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

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
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.Adress;
import net.hycollege.mybatis.domain.User;
import net.hycollege.portal.util.CookieUtils;
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
	public User select(String username, String password) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("username", username);
			body.add("password", password);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			User user = restTemplate.postForObject("http://47.103.217.104:8082/user/login", request, User.class);
			user.setPassword(null);
			String uid = user.getUid();
			redisTemplate.opsForValue().set(uid, objectMapper.writeValueAsString(user));
			redisTemplate.expire(uid, thirtyMinutes, TimeUnit.SECONDS);
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
		try {
			return postCU(objectMapper.writeValueAsString(user), "http://47.103.217.104:8082/user/register");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@Override
	public Message update(User user, HttpServletRequest request) {
		try {
			String string = objectMapper.writeValueAsString(user);
			Message message = postCU(string, "http://47.103.217.104:8082/user/update");
			user.setPassword(null);
			String token = CookieUtils.getToken(request);
			redisTemplate.opsForValue().set(token, objectMapper.writeValueAsString(user));
			return message;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	private Message postCU(String json, String urlInterface) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> request = new HttpEntity<>(json, header);
			return restTemplate.postForObject(urlInterface, request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	private Message postD(String urlInterface, String name, String id) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add(name, id);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject(urlInterface, request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	private <T> T getR(String urlInterface, String appendGetContent, Class<T> clazz) {
		try {
			return restTemplate.getForObject(urlInterface + appendGetContent, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message insertUserAdress(Adress adress) {
		try {
			return postCU(objectMapper.writeValueAsString(adress), "http://47.103.217.104:8082/user/adress/insert");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new Message(Message.fail);
	}

	@Override
	public Message updateUserAdress(Adress adress) {
		try {
			return postCU(objectMapper.writeValueAsString(adress), "http://47.103.217.104:8082/user/adress/update");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new Message(Message.fail);
	}

	@Override
	public Message deleteUserAdress(String aid) {
		return postD("http://47.103.217.104:8082/user/adress/delete", "aid", aid);
	}

	@Override
	public LayUITableMessage selectUserAdress(String auid) {
		return getR("http://47.103.217.104:8082/user/adress/select", "?auid=" + auid, LayUITableMessage.class);
	}

	@Override
	public User getUser(HttpServletRequest request) {
		String token = CookieUtils.getToken(request);
		try {
			return objectMapper.readValue((String) redisTemplate.opsForValue().get(token), User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
