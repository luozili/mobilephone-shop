package net.hycollege.portal.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.ESMssage;
import net.hycollege.protal.services.ProductService;


@RestController
public class ProductControll {
	@Autowired
	private ProductService productService;
	@GetMapping("/product/search")
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		ESMssage searchProduct = productService.searchProduct(productName, pageIndex, size);
		return searchProduct;
	}
}
