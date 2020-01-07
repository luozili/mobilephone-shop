package net.hycollege.portal.controll;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.config.AlipayConfig;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ali.AliForm;
import net.hycollege.protal.services.PayService;

@RestController
public class PayControll {
	@Autowired
	private PayService payService;

	@PostMapping("/alipay")
	public void createAliPayForm(HttpServletRequest request, HttpServletResponse httpResponse,
			@RequestBody AliForm aliForm) throws IOException, AlipayApiException {
		String form = payService.createAliPayForm(aliForm, request);
		httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@PostMapping("/alipay/notify/callback")
	public void notifyResult(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
		System.out.println(JSON.toJSONString(map));
	}

	@GetMapping("/alipay/callback")
	public void returnResult(HttpServletResponse response, @RequestParam Map<String, String> map) throws Exception {
		AlipayTradePayResponse alipayTradePayResponse = JSON.parseObject(JSON.toJSONString(map),
				AlipayTradePayResponse.class);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse((String) map.get("timestamp"));
		alipayTradePayResponse.setGmtPayment(date);
		Message message = payService.updateForOrder(alipayTradePayResponse);
		if (message.getStatus() == Message.ok)
			response.sendRedirect("/index.html");
	}

	@PostMapping("/order/refund")
	public Message name(String out_trade_no) {
		return payService.alipayTradeRefund(out_trade_no);
	}
}
