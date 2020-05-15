package net.hycollege.message.bean.product;

import net.hycollege.mybatis.domain.Product;

public class ShopCarMessage extends Product {
	private int pnumber;
	private String cid;
	public ShopCarMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopCarMessage(int pnumber, String cid) {
		super();
		this.pnumber = pnumber;
		this.cid = cid;
	}

	public ShopCarMessage(int pnumber) {
		super();
		this.pnumber = pnumber;
	}

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
