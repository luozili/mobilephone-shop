package net.hycollege.protal.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alipay.api.response.AlipayTradePayResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.mybatis.domain.User;
import net.hycollege.protal.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public LayUITableMessage getOrder(String token) {
		String uid = getUserId(token);
		StringBuffer url = new StringBuffer("http://47.103.217.104:8086/order/gets?");
		url.append("uid=").append(uid);
		return restTemplate.getForObject(url.toString(), LayUITableMessage.class);
	}

	private String getUserId(String token) {
		String user_string = (String) redisTemplate.opsForValue().get(token);
		User user = null;
		try {
			user = objectMapper.readValue(user_string, User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user.getUid();
	}

	@Override
	public void updateOrder(AlipayTradePayResponse alipayTradePayResponse) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("out_trade_no", alipayTradePayResponse.getOutTradeNo());
			body.add("gmt_payment", alipayTradePayResponse.getGmtPayment());
			body.add("trade_no", alipayTradePayResponse.getTradeNo());
			body.add("total_amount", alipayTradePayResponse.getTotalAmount());
			body.add("trade_status", "已付款");
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, header);
			restTemplate.postForObject("http://47.103.217.104:8086/order/update", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public LayUITableMessage getOrderProduct(String out_trade_no) {
		StringBuffer url = new StringBuffer("http://47.103.217.104:8086/order/gets/products?");
		url.append("out_trade_no=").append(out_trade_no);
		return restTemplate.getForObject(url.toString(), LayUITableMessage.class);
	}
}
