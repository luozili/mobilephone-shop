package net.hycollege.admin.console.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.hycollege.admin.console.services.OrderService;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public Message alipayTradeRefund(String out_trade_no) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("out_trade_no", out_trade_no);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);

			return restTemplate.postForObject("http://193.112.173.11:8086/order/refund", requestEntity, Message.class);
		} catch (Exception e) {
			System.out.println(e);
			return new Message(Message.fail);
		}
	}

	@Override
	public LayUITableMessage getOrder(int currentPage, int size) {
		try {
			return restTemplate.getForObject(
					"http://193.112.173.11:8086/order/admin/gets?currentPage=" + currentPage + "&size=" + size,
					LayUITableMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
