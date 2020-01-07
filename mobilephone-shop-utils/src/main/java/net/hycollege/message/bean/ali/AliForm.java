package net.hycollege.message.bean.ali;

import java.util.List;

public class AliForm {
	private List<AliItem> aliItems;

	public AliForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AliForm(List<AliItem> aliItems) {
		super();
		this.aliItems = aliItems;
	}

	public List<AliItem> getAliItems() {
		return aliItems;
	}

	public void setAliItems(List<AliItem> aliItems) {
		this.aliItems = aliItems;
	}
	
}
