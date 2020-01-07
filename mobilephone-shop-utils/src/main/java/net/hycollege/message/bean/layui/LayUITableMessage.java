package net.hycollege.message.bean.layui;

import java.util.List;

public class LayUITableMessage {
	private int code = 0;
	private String msg;
	private long count;
	private List<?> data;
	
	public LayUITableMessage(int code) {
		super();
		this.code = code;
	}
	public LayUITableMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}

}
