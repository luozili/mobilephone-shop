package net.hycollege.message.bean.ali;

import java.util.List;

public class AliRequestOrder {
	private float refund_amount;
	private String out_trade_no;
	private String trade_no;
	private String org_pid;
	private List<String> query_options;
	public AliRequestOrder() {
		super();
	}
	public AliRequestOrder(String out_trade_no, String trade_no, String org_pid, List<String> query_options) {
		super();
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.org_pid = org_pid;
		this.query_options = query_options;
	}
	
	public AliRequestOrder(float refund_amount, String out_trade_no, String trade_no, String org_pid,
			List<String> query_options) {
		super();
		this.refund_amount = refund_amount;
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.org_pid = org_pid;
		this.query_options = query_options;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOrg_pid() {
		return org_pid;
	}
	public void setOrg_pid(String org_pid) {
		this.org_pid = org_pid;
	}
	public List<String> getQuery_options() {
		return query_options;
	}
	public void setQuery_options(List<String> query_options) {
		this.query_options = query_options;
	}
	public float getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(float refund_amount) {
		this.refund_amount = refund_amount;
	}
	
}
