/**
  * Copyright 2019 bejson.com 
  */
package net.hycollege.message.bean.ali;

public class AliGoods {

	private String goods_id;
	private String goods_name;
	private int quantity;
	private float price;

	public AliGoods() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AliGoods(String goods_id, String goods_name, float price, int quantity) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.price = price;
		this.quantity = quantity;
	}


	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_name() {
		return goods_name;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}