package net.hycollege.message.bean.order;

public class OrderMessage {
	private String oid;
	private String uid;
	private String out_trade_no;
	private String trade_no;
	private String gmt_payment;
	private float total_amount;
	private String trade_status;
	private String opid;
	private String pid;
	private int pnumber;
	public OrderMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderMessage(String oid, String uid, String out_trade_no, String trade_no, String gmt_payment,
			float total_amount, String trade_status, String opid, String pid, int pnumber) {
		super();
		this.oid = oid;
		this.uid = uid;
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.gmt_payment = gmt_payment;
		this.total_amount = total_amount;
		this.trade_status = trade_status;
		this.opid = opid;
		this.pid = pid;
		this.pnumber = pnumber;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getGmt_payment() {
		return gmt_payment;
	}
	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getOpid() {
		return opid;
	}
	public void setOpid(String opid) {
		this.opid = opid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	
}
