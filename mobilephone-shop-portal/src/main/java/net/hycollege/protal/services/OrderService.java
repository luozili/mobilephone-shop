package net.hycollege.protal.services;

import com.alipay.api.response.AlipayTradePayResponse;

import net.hycollege.message.bean.layui.LayUITableMessage;

public interface OrderService {
	public LayUITableMessage getOrder(String token);
	public void updateOrder(AlipayTradePayResponse alipayTradePayResponse);
	public LayUITableMessage getOrderProduct(String out_trade_no);
}
