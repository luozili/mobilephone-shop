package net.hycollege.message.bean.ali;

import java.util.List;

public class AliPay {
	/**
	 * 商户订单号
	 */
	private String ut_trade_no;
	private float total_amount;
	private String subject;
	private static final String product_code = "FAST_INSTANT_TRADE_PAY";
	private List<AliGoods> aliGoodses;
	
	public AliPay(String ut_trade_no, float total_amount, String subject, List<AliGoods> aliGoodses) {
		super();
		this.ut_trade_no = ut_trade_no;
		this.total_amount = total_amount;
		this.subject = subject;
		this.aliGoodses = aliGoodses;
	}
	public AliPay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUt_trade_no() {
		return ut_trade_no;
	}
	public void setUt_trade_no(String ut_trade_no) {
		this.ut_trade_no = ut_trade_no;
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
	public static String getProductCode() {
		return product_code;
	}
	public List<AliGoods> getAliGoodses() {
		return aliGoodses;
	}
	public void setAliGoodses(List<AliGoods> aliGoodses) {
		this.aliGoodses = aliGoodses;
	}
	@Override
	public String toString() {
		return "AliPay [ut_trade_no=" + ut_trade_no + ", total_amount=" + total_amount + ", subject=" + subject + "]";
	}
	
}
