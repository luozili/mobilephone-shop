package net.hycollege.message.bean.ali;

public class AliInfo {
	private String out_trade_no;
	private String trade_no;
	private float refund_amount;

	public AliInfo() {
		super();
	}

	public AliInfo(String out_trade_no, String trade_no, float refund_amount) {
		super();
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
		this.refund_amount = refund_amount;
	}

	public AliInfo(String out_trade_no, String trade_no) {
		super();
		this.out_trade_no = out_trade_no;
		this.trade_no = trade_no;
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

	public float getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(float refund_amount) {
		this.refund_amount = refund_amount;
	}

	@Override
	public String toString() {
		return "AliReturn [out_trade_no=" + out_trade_no + ", trade_no=" + trade_no + "]";
	}

}
