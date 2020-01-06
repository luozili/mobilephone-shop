package net.hycollege.order.controll;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.order.services.OrderService;

@RestController
public class OrderControll {
	@Autowired
	private OrderService orderPayService;

	@PostMapping("/order/insert")
	public Message insertOrder(String uid, String pid, int pnumber, String out_trade_no) {
		return orderPayService.insert2AliPay(uid, pid, pnumber, out_trade_no);
	}

	@PostMapping("/order/query")
	public Message queryOrderInfo(String uid) {
		return orderPayService.query2AliPay(uid);
	}

	@PostMapping("/order/refund")
	public Message alipayTradeRefund(String out_trade_no) {
		return orderPayService.alipayTradeRefund(out_trade_no);
	}

	@PostMapping("/order/update")
	public Message updateOrder(String out_trade_no, String gmt_payment, String trade_no, float total_amount,
			String trade_status) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(gmt_payment);
		return orderPayService.updateOrder(out_trade_no, format.format(date), trade_no, total_amount, trade_status);
	}

	@PostMapping("/order/update/status")
	public Message updateOrder(String out_trade_no, String trade_status) {
		return orderPayService.updateOrder2Status(out_trade_no, trade_status);
	}

	@GetMapping("/order/gets")
	public LayUITableMessage getOrder2Uid(String uid) {
		return orderPayService.getOrder(uid);
	}

	@GetMapping("/order/admin/gets")
	public LayUITableMessage getOrder(int currentPage, int size) {
		return orderPayService.getOrder(currentPage, size);
	}
	@GetMapping("/order/gets/products")
	public LayUITableMessage getOrderProducts(String out_trade_no) {
		return orderPayService.getOrderProduct(out_trade_no);
	}
}
