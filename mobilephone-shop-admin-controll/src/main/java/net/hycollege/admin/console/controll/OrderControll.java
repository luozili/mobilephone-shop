package net.hycollege.admin.console.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.admin.console.services.OrderService;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.layui.LayUITableMessage;

@RestController
public class OrderControll {
	@Autowired
	private OrderService orderService;
	@GetMapping("/order/admin/gets")
	public LayUITableMessage getOrder(int currentPage, int size) {
		return orderService.getOrder(currentPage, size);
	}

	@PostMapping("/order/refund")
	public Message alipayTradeRefund(String out_trade_no) {
		return orderService.alipayTradeRefund(out_trade_no);
	}
}
