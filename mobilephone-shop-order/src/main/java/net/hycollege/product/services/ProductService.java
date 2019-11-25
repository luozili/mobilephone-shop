package net.hycollege.product.services;


import net.hycollege.message.bean.LayUITableMessage;
import net.hycollege.message.bean.ProductMessage;

public interface ProductService {
	public ProductMessage selectOneProduct(String productID);
	public LayUITableMessage getProducts(int currentPage, int pageSize);
}
