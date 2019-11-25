package net.hycollege.message.bean;
/**
 * include Message all field, and have es search all totalCount
 * @author king
 *
 */
public class ESMssage extends Message {
	private long totalCount;

	public ESMssage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ESMssage(int status, int errer, String[] data, Object message) {
		super(status, errer, data, message);
		// TODO Auto-generated constructor stub
	}

	public ESMssage(long totalCount) {
		super();
		this.totalCount = totalCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
