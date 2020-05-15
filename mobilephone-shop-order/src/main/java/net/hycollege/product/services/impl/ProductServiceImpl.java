package net.hycollege.product.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ProductMessage selectOneProduct(String productID) {
		try {
			return restTemplate.getForObject("http://193.112.173.11:8084/product/get?productID=" + productID,
					ProductMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}


	@Override
	public LayUITableMessage getProducts(int currentPage, int pageSize) {
		try {
			return restTemplate.getForObject(
					"http://193.112.173.11:8084/product/gets?currentPage=" + currentPage + "&pageSize=" + pageSize,
					LayUITableMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
