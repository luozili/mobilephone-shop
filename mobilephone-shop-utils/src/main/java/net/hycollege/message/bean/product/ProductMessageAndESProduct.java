package net.hycollege.message.bean.product;

import net.hycollege.message.bean.elasticsearch.ESProduct;

public class ProductMessageAndESProduct {
	private ProductMessage productMessage;
	private ESProduct esProduct;
	public ProductMessageAndESProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductMessageAndESProduct(ProductMessage productMessage, ESProduct esProduct) {
		super();
		this.productMessage = productMessage;
		this.esProduct = esProduct;
	}
	public ProductMessage getProductMessage() {
		return productMessage;
	}
	public void setProductMessage(ProductMessage productMessage) {
		this.productMessage = productMessage;
	}
	public ESProduct getEsProduct() {
		return esProduct;
	}
	public void setEsProduct(ESProduct esProduct) {
		this.esProduct = esProduct;
	}
	
}
