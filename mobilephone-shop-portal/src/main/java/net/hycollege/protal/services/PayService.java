package net.hycollege.protal.services;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.response.AlipayTradePayResponse;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ali.AliForm;
import net.hycollege.message.bean.layui.LayUITableMessage;

public interface PayService {

	public String createAliPayForm(AliForm aliForm, HttpServletRequest request);

	public Message alipayTradeRefund(String out_trade_no);

	public Message updateForOrder(AlipayTradePayResponse alipayTradePayResponse);

	
}
