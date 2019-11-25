package net.hycollege.message.bean.ali;

public class AliItem {
	private String pid;
	private int buy_number;
	public AliItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AliItem(String pid, int buy_number) {
		super();
		this.pid = pid;
		this.buy_number = buy_number;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getBuy_number() {
		return buy_number;
	}
	public void setBuy_number(int buy_number) {
		this.buy_number = buy_number;
	}
	
}
