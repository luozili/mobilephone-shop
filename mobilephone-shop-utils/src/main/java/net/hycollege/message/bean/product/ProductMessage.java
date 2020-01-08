package net.hycollege.message.bean.product;

import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.ProductDesc;
/**
 * ProductMessage
 * @author king
 * include Product product and ProductDesc productDesc
 */
public class ProductMessage {
	private Product product;
	private ProductDesc productDesc;

	public ProductMessage(Product product, ProductDesc productDesc) {
		super();
		this.product = product;
		this.productDesc = productDesc;
	}

	public ProductMessage() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductDesc getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(ProductDesc productDesc) {
		this.productDesc = productDesc;
	}

}
