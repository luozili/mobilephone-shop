package net.hycollege.order.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.hycollege.message.bean.ProductMessage;
import net.hycollege.message.bean.ali.AliForm;
import net.hycollege.message.bean.ali.AliGoods;
import net.hycollege.message.bean.ali.AliItem;
import net.hycollege.message.bean.ali.AliPay;
import net.hycollege.message.utils.CheckObjects;
import net.hycollege.order.services.OrederPayService;
import net.hycollege.product.services.ProductService;

public class OrederPayServiceImpl implements OrederPayService{
	@Autowired
	private ProductService productService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createForm(AliForm aliForm) {
		List<AliItem> aliItems = aliForm.getAliItems();
		AliPay aliPay = new AliPay();
		List<AliGoods> aliGoodses = new ArrayList<AliGoods>();
		float total_amount = 0;
		for (AliItem aliItem : aliItems) {
			ProductMessage productMessage = productService.selectOneProduct(aliItem.getPid());
			total_amount += productMessage.getProduct().getPrices() * aliItem.getBuy_number();
		}
		String ut_trade_no = (String) redisTemplate.opsForValue().get("ut_trade_no");
		if(CheckObjects.checkObjectNull(ut_trade_no) || CheckObjects.checkLeng(ut_trade_no))
			redisTemplate.opsForValue().set("ut_trade_no", new Date().getTime());
		aliPay.setUt_trade_no(ut_trade_no);
		int parseInt = Integer.parseInt(ut_trade_no);
		parseInt++;
		redisTemplate.opsForValue().set("ut_trade_no", parseInt);
		aliPay.setAliGoodses(aliGoodses);
		aliPay.setTotal_amount(total_amount);
		
	}

}
