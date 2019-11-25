package net.hycollege.services;

import java.util.List;

import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.LayUITableMessage;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ProductMessage;
import net.hycollege.mybatis.domain.Product;

public interface ProductService {
	public void addProduct(Product product, String pdesc, ESProduct esProduct);
	public ProductMessage selectOneProduct(String productID);
	public Message deleteOneProduct(String productID);
	public Message updateOneProduct(ProductMessage productMessage, ESProduct esProduct);
	public Message updateOneProductStatus(String productID, String status);
	public LayUITableMessage getProducts(int currentPage, int pageSize);
}
