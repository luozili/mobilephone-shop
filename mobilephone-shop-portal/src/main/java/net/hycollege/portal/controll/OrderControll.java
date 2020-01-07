package net.hycollege.portal.controll;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradePayResponse;

import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.portal.util.CookieUtils;
import net.hycollege.protal.services.OrderService;

@RestController
public class OrderControll {
	@Autowired
	private OrderService orderService;

	@GetMapping("/alipaycallback")
	public void notifyResult(HttpServletResponse response, HttpServletRequest request,
			@RequestParam Map<String, String> map) throws Exception {
		AlipayTradePayResponse alipayTradePayResponse = JSON.parseObject(JSON.toJSONString(map),
				AlipayTradePayResponse.class);
		orderService.updateOrder(alipayTradePayResponse);
		response.sendRedirect("/index.html");
	}

	@GetMapping("/order/gets")
	public LayUITableMessage name(HttpServletRequest request) {
		String token = CookieUtils.getToken(request);
		return orderService.getOrder(token);
	}
	@GetMapping("/order/gets/products")
	public LayUITableMessage name(String out_trade_no) {
		return orderService.getOrderProduct(out_trade_no);
	}
}
