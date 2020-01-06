package net.hycollege.product.manage.services;

import java.util.List;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
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
	public Message addProduct2UserCar(String pid, String uid, int pnumber);
	public Message deleteProduct2UserCar(String cid);
	public Message updateProduct2UserCarPnumber(String cid, int pnumber);
	public LayUITableMessage selectProduct2UserCar(String uid);
	public Message selectProduct2UserCarExist(String pid, String uid);
	public Message deleteProduct2UserCarOnPid(String uid, String pid);
}
