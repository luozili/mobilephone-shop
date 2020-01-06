package net.hycollege.message.bean;

import java.util.Arrays;

public class Message {
	public static final int ok = 200;
	public static final int fail = 100;
	private int status = 200;
	private int errno = 0;
	private String[] data;
	private Object message;
	public Message() {
		super();
	}
	
	public Message(int status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Message(int status, int errno, String[] data, Object message) {
		super();
		this.status = status;
		this.errno = errno;
		this.data = data;
		this.message = message;
	}

	public Message(int status) {
		super();
		this.status = status;
	}

	public Message(String ...data) {
		this.data = data;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
		return "Message [status=" + status + ", errno=" + errno + ", data=" + Arrays.toString(data) + ", message="
				+ message + "]";
	}

	
}
