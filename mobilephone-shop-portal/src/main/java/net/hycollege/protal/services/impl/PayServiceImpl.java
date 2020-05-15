package net.hycollege.protal.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.config.AlipayConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ali.AliForm;
import net.hycollege.message.bean.ali.AliGoods;
import net.hycollege.message.bean.ali.AliItem;
import net.hycollege.message.bean.ali.AliPay;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.User;
import net.hycollege.portal.util.CookieUtils;
import net.hycollege.protal.services.PayService;
import net.hycollege.protal.services.ProductService;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ProductService productService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String createAliPayForm(AliForm aliForm, HttpServletRequest request) {
		String token = CookieUtils.getToken(request);
		String uid = getUserId(token);
		AliPay aliPay = setAliRequestForm(aliForm, uid);
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				"RSA2"); // 获得初始化的AlipayClient

		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 在公共参数中设置回跳和通知地址
		try {
			alipayRequest.setBizContent(objectMapper.writeValueAsString(aliPay));

		} catch (Exception e1) {
			e1.printStackTrace();
		} // 填充业务参数
		String form = null;
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return form;
	}

	private String getUserId(String token) {
		String user_string = (String) redisTemplate.opsForValue().get(token);
		User user = null;
		try {
			user = objectMapper.readValue(user_string, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user.getUid();
	}

	private AliPay setAliRequestForm(AliForm aliForm, String uid) {
		List<AliItem> aliItems = aliForm.getAliItems();
		AliPay aliPay = new AliPay();
		List<AliGoods> aliGoodses = new ArrayList<AliGoods>();
		float total_amount = 0;
		AliGoods aliGoods = null;
		Product product = null;
		ProductMessage productMessage = null;
		Float prices;
		int goods_number;
		if (!redisTemplate.hasKey("out_trade_no"))
			redisTemplate.opsForValue().set("out_trade_no", 20190000000000001l);
		Long out_trade_no = redisTemplate.opsForValue().increment("out_trade_no");
		String out_trade_no_string = out_trade_no.toString();
		for (AliItem aliItem : aliItems) {
			productMessage = productService.selectProduct(aliItem.getPid());
			product = productMessage.getProduct();

			prices = product.getPrices();
			goods_number = aliItem.getBuy_number();
			aliGoods = new AliGoods(product.getPname(), product.getPname(), prices, goods_number);
			aliGoodses.add(aliGoods);
			total_amount += prices * goods_number;
			addProduct2Order(uid, product.getPid(), goods_number, out_trade_no_string);
		}

		aliPay.setOut_trade_no(out_trade_no_string);
		aliPay.setGoods_detail(aliGoodses);
		aliPay.setTotal_amount(total_amount);
		aliPay.setSubject("purchase");
		return aliPay;
	}

	private void addProduct2Order(String uid, String pid, int pnumber, String out_trade_no) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("pid", pid);
			body.add("uid", uid);
			body.add("pnumber", pnumber);
			body.add("out_trade_no", out_trade_no);
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, header);
			restTemplate.postForObject("http://193.112.173.11:8086/order/insert", request,
					Message.class);
//			if(postForObject.getStatus() == Message.ok)
//				System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message alipayTradeRefund(String out_trade_no) {
		try {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("out_trade_no", out_trade_no);
			HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, header);
			return restTemplate.postForObject("http://193.112.173.11:8086/order/refund", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Message(Message.fail);
	}

	@Override
	public Message updateForOrder(AlipayTradePayResponse alipayTradePayResponse) {
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
			return restTemplate.postForObject("http://193.112.173.11:8086/order/update", request, Message.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	

	

}
