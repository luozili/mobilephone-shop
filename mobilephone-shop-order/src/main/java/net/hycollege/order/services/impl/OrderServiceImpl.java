package net.hycollege.order.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.config.AlipayConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;

import net.hycollege.mapper.OrderMapper;
import net.hycollege.mapper.OrderProductMapper;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ali.AliRequestOrder;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.message.bean.product.ShopCarMessage;
import net.hycollege.mybatis.domain.Order;
import net.hycollege.mybatis.domain.OrderExample;
import net.hycollege.mybatis.domain.OrderProduct;
import net.hycollege.mybatis.domain.OrderProductExample;
import net.hycollege.order.services.OrderService;
import net.hycollege.product.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private OrderProductMapper orderProductMapper;
	@Autowired
	private ProductService productService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Message insert2AliPay(String uid, String pid, int pnumber, String out_trade_no) {
		if (out_trade_no == null)
			return new Message(Message.fail);

		OrderExample example = new OrderExample();
		example.createCriteria().andOutTradeNoEqualTo(out_trade_no);
		List<Order> list = orderMapper.selectByExample(example);
		String oid = UUID.randomUUID().toString();
		if (list != null && list.size() > 0)
			oid = list.get(0).getOid();
		Order order = new Order();
		order.setOid(oid);
		order.setUid(uid);
		order.setOutTradeNo(out_trade_no);
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOpid(UUID.randomUUID().toString());
		orderProduct.setOid(oid);
		orderProduct.setPid(pid);
		orderProduct.setPnumber(pnumber);
		int insert = orderMapper.insert(order);
		int insert2 = orderProductMapper.insert(orderProduct);
		if (insert > 0 && insert2 > 0)
			return new Message();
		return new Message(Message.fail);
	}

	@Override
	public Message query2AliPay(String uid) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				"RSA2"); // 获得初始化的AlipayClient
		OrderExample example = new OrderExample();
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		example.createCriteria().andUidEqualTo(uid);
		List<Order> selectByExample = orderMapper.selectByExample(example);
		List<AlipayTradeQueryResponse> list = new ArrayList<AlipayTradeQueryResponse>();
		for (Order order : selectByExample) {
			try {
				AliRequestOrder aliQueryOrder = new AliRequestOrder();
				aliQueryOrder.setOut_trade_no(order.getOutTradeNo());
				aliQueryOrder.setTrade_no(order.getTradeNo());
				request.setBizContent(objectMapper.writeValueAsString(aliQueryOrder));
				AlipayTradeQueryResponse response = alipayClient.execute(request);
				if (response.isSuccess()) {
					AlipayTradeQueryResponse alipayTradeQueryResponse = new AlipayTradeQueryResponse();
					alipayTradeQueryResponse.setOutTradeNo(response.getOutTradeNo());
					alipayTradeQueryResponse.setMsg(response.getMsg());
					alipayTradeQueryResponse.setTotalAmount(response.getTotalAmount());
					alipayTradeQueryResponse.setTradeNo(response.getTradeNo());
					if (response.getTradeStatus().equals("TRADE_SUCCESS"))
						list.add(alipayTradeQueryResponse);
					System.out.println(objectMapper.writeValueAsString(alipayTradeQueryResponse));
					System.out.println("调用成功");
				} else {
					System.out.println("调用失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new Message(Message.fail);
			}
		}
		return new Message(Message.ok, list);
	}

	@Override
	public Message alipayTradeRefund(String out_trade_no) {
		try {
			OrderExample orderExample = new OrderExample();
			orderExample.createCriteria().andOutTradeNoEqualTo(out_trade_no).andTradeStatusNotEqualTo("退款");
			List<Order> list = orderMapper.selectByExample(orderExample);
			if (list == null || list.size() == 0) {
				return new Message(Message.fail);
			}
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
					"RSA2");
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			AliRequestOrder aliRequestOrder = new AliRequestOrder();
			aliRequestOrder.setOut_trade_no(out_trade_no);
			Order order2 = list.get(0);
			aliRequestOrder.setRefund_amount(order2.getTotalAmount());
			request.setBizContent(objectMapper.writeValueAsString(aliRequestOrder));
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				OrderExample example = new OrderExample();
				example.createCriteria().andOutTradeNoEqualTo(out_trade_no);
				Order order = new Order();
				order.setTradeStatus("退款");
				orderMapper.updateByExampleSelective(order, example);
				return new Message();
			} else {
				return new Message(Message.fail);
			}
		} catch (Exception e) {
			return new Message(Message.fail);
		}
	}

	@Override
	public Message updateOrder(String out_trade_no, String gmt_payment, String trade_no, float total_amount,
			String trade_status) {
		Order order = new Order();
		order.setGmtPayment(gmt_payment);
		order.setTradeNo(trade_no);
		order.setTotalAmount(total_amount);
		order.setTradeStatus(trade_status);
		OrderExample example = new OrderExample();
		example.createCriteria().andOutTradeNoEqualTo(out_trade_no);
		int i = orderMapper.updateByExampleSelective(order, example);
		if (i > 0)
			return new Message();
		return new Message(Message.fail);
	}

	@Override
	public Message updateOrder2Status(String out_trade_no, String trade_status) {
		Order order = new Order();
		order.setTradeStatus(trade_status);
		OrderExample example = new OrderExample();
		example.createCriteria().andOutTradeNoEqualTo(out_trade_no);
		int i = orderMapper.updateByExampleSelective(order, example);
		if (i > 0)
			return new Message();
		return new Message(Message.fail);
	}

	@Override
	public LayUITableMessage getOrder(String uid) {
		OrderExample example = new OrderExample();
		example.createCriteria().andUidEqualTo(uid);
		List<Order> data = orderMapper.selectByExample(example);
		LayUITableMessage layUITableMessage = new LayUITableMessage();
		layUITableMessage.setData(data);
		return layUITableMessage;
	}

	@Override
	public LayUITableMessage getOrder(int currentPage, int size) {
		PageHelper.startPage(currentPage, size);
		List<Order> list = orderMapper.selectByExample(null);
		LayUITableMessage layUITableMessage = new LayUITableMessage();
		layUITableMessage.setData(list);
		return layUITableMessage;
	}

	@Override
	public LayUITableMessage getOrderProduct(String out_trade_no) {
		OrderExample example = new OrderExample();
		example.createCriteria().andOutTradeNoEqualTo(out_trade_no);
		List<Order> list = orderMapper.selectByExample(example);
		if (list == null || list.size() == 0)
			return null;
		Order order = list.get(0);
		OrderProductExample orderProductExample = new OrderProductExample();
		orderProductExample.createCriteria().andOidEqualTo(order.getOid());
		List<OrderProduct> orderProducts = orderProductMapper.selectByExample(orderProductExample);
		LayUITableMessage layUITableMessage = new LayUITableMessage();
		List<ShopCarMessage> data = new ArrayList<ShopCarMessage>();
		ShopCarMessage shopCarMessage = null;
		ProductMessage selectOneProduct = null;
		for (OrderProduct orderProduct : orderProducts) {

			selectOneProduct = productService.selectOneProduct(orderProduct.getPid());
			try {
				String product_json = objectMapper.writeValueAsString(selectOneProduct.getProduct());
				shopCarMessage = objectMapper.readValue(product_json, ShopCarMessage.class);
				shopCarMessage.setPnumber(orderProduct.getPnumber());
				data.add(shopCarMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		layUITableMessage.setData(data);
		return layUITableMessage;
	}

}
