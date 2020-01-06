package net.hycollege.protal.services;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;

public interface ProductService {

	public ESMssage searchProduct(String productName, int pageIndex, int size);
	public Message addProduct2UserCar(String pid, String token, int pnumber);
	public Message deleteProduct2UserCar(String cid);
	public Message updateProduct2UserCarPnumber(String cid, int pnumber);
	public LayUITableMessage selectProduct2UserCar(String token);
	public ProductMessage selectProduct(String pid);
	public Message selectProduct2UserCarExist(String pid, String token);
	public Message deleteProduct2UserCar(String token, String pid);
}
