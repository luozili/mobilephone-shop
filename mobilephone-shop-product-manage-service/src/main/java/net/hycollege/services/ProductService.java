package net.hycollege.services;

import java.util.List;

import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.ProductMessage;
import net.hycollege.mybatis.domain.Product;

public interface ProductService {
	public void addProduct(ProductMessage productMessage, ESProduct esProduct);
	public List<Product> selectProduct(int currentPage, int pageSize);
	public long selectAllProductsConunt();
	public ProductMessage selectOneProduct(String productID);
	public void deleteOneProduct(String productID);
	public void updateOneProduct(ProductMessage productMessage, ESProduct esProduct);
	public void addProduct2Elasticsearch(ESProduct esProduct);
	public void updateOneProductStatus(String productID, String status);
}
