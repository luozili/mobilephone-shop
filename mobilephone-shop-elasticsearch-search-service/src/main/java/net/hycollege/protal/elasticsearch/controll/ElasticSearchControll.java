package net.hycollege.protal.elasticsearch.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.ESMssage;
import net.hycollege.protal.elasticsearch.service.ProductSearchService;

@RestController
public class ElasticSearchControll {
	@Autowired
	private ProductSearchService productSearchService;
	@GetMapping("/product/search")
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		return productSearchService.searchProduct(productName, pageIndex, size);
	}
}
