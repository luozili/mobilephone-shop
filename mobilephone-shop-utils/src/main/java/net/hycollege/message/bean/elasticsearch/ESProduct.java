package net.hycollege.message.bean.elasticsearch;
/**
 * have product all field
 * @author king
 *
 */
public class ESProduct {
	private String pid;

	private String picture;

	private String pname;
	private String status;
	private Float prices;
	private String pdesc;

	public ESProduct(String pid, String picture, String pname, String status, Float prices, String pdesc) {
		super();
		this.pid = pid;
		this.picture = picture;
		this.pname = pname;
		this.status = status;
		this.prices = prices;
		this.pdesc = pdesc;
	}

	public ESProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture == null ? null : picture.trim();
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname == null ? null : pname.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Float getPrices() {
		return prices;
	}

	public void setPrices(Float prices) {
		this.prices = prices;
	}
}
