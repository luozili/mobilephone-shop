package net.hycollege.admin.console.services;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;

public interface OrderService {

	public Message alipayTradeRefund(String out_trade_no);

	public LayUITableMessage getOrder(int currentPage, int size);

}
