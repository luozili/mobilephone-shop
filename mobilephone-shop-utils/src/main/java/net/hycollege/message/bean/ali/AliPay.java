package net.hycollege.message.bean.ali;

import java.util.List;

public class AliPay {
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	private String product_code = "FAST_INSTANT_TRADE_PAY";
	private float total_amount;
	private String subject;
	private List<AliGoods> goods_detail;

	public AliPay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AliPay(String out_trade_no, String product_code, float total_amount, String subject,
			List<AliGoods> goods_detail) {
		super();
		this.out_trade_no = out_trade_no;
		this.product_code = product_code;
		this.total_amount = total_amount;
		this.subject = subject;
		this.goods_detail = goods_detail;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public List<AliGoods> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<AliGoods> goods_detail) {
		this.goods_detail = goods_detail;
	}


}
