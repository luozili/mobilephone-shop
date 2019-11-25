package net.hycollege.message.bean;

import java.util.Arrays;

public class Message {
	public static final int ok = 200;
	public static final int fail = 100;
	private int status = 200;
	private int errer = 0;
	private String[] data;
	private Object message;
	public Message() {
		super();
	}
	
	public Message(int status) {
		super();
		this.status = status;
	}

	public Message(int status, int errer, String[] data, Object message) {
		super();
		this.status = status;
		this.errer = errer;
		this.data = data;
		this.message = message;
	}
	public Message(String ...data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getErrer() {
		return errer;
	}
	public void setErrer(int errer) {
		this.errer = errer;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [status=" + status + ", errer=" + errer + ", data=" + Arrays.toString(data) + ", message="
				+ message + "]";
	}

	
}
