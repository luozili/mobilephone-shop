package net.hycollege.order.services;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;

public interface OrderService {
	public Message insert2AliPay(String uid, String pid, int pnumber, String out_trade_no);

	public Message query2AliPay(String uid);

	public Message alipayTradeRefund(String out_trade_no);

	public Message updateOrder(String out_trade_no, String gmt_payment, String trade_no, float total_amount,
			String trade_status);

	public Message updateOrder2Status(String out_trade_no, String trade_status);

	public LayUITableMessage getOrder(int currentPage, int size);

	public LayUITableMessage getOrder(String uid);
	public LayUITableMessage getOrderProduct(String out_trade_no);
}
