package net.hycollege.protal.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.mybatis.domain.User;
import net.hycollege.protal.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		try {
			StringBuilder sb = new StringBuilder("http://47.103.217.104:8085/es/product/search");
			sb.append("?productName=").append(productName).append("&pageIndex=").append(pageIndex).append("&size=")
					.append(size);
			return restTemplate.getForObject(sb.toString(), ESMssage.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Message addProduct2UserCar(String pid, String token, int pnumber) {
		try {

			String uid = null;
			User user = objectMapper.readValue((String) redisTemplate.opsForValue().get(token), User.class);
			uid = user.getUid();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("pid", pid);
			body.add("uid", uid);
			body.add("pnumber", pnumber + "");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://47.103.217.104:8084/product/car/add", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@Override
	public Message deleteProduct2UserCar(String cid) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("cid", cid);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://47.103.217.104:8084/product/car/delete", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@Override
	public Message updateProduct2UserCarPnumber(String cid, int pnumber) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("cid", cid);
			body.add("pnumber", pnumber + "");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://47.103.217.104:8084/product/car/update/pnumber", request,
					Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@Override
	public LayUITableMessage selectProduct2UserCar(String token) {
		try {
			
			String uid = objectMapper.readValue((String) redisTemplate.opsForValue().get(token), User.class).getUid();
			return restTemplate.getForObject("http://47.103.217.104:8084/product/car/select?uid=" + uid,
					LayUITableMessage.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductMessage selectProduct(String pid) {
		try {
			StringBuilder sb = new StringBuilder("http://47.103.217.104:8084/product/get");
			sb.append("?productID=").append(pid);
			return restTemplate.getForObject(sb.toString(), ProductMessage.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message selectProduct2UserCarExist(String pid, String token) {
		try {
			User user = objectMapper.readValue((String) redisTemplate.opsForValue().get(token), User.class);
			StringBuilder sb = new StringBuilder("http://47.103.217.104:8084/product/car/exist");
			String uid = user.getUid();
			sb.append("?pid=").append(pid).append("&uid=").append(uid);
			return restTemplate.getForObject(sb.toString(), Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message deleteProduct2UserCar(String token, String pid) {
		try {
			User user = objectMapper.readValue((String) redisTemplate.opsForValue().get(token), User.class);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("uid", user.getUid());
			body.add("pid", pid);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://47.103.217.104:8084/product/car/delete/pid", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

}
