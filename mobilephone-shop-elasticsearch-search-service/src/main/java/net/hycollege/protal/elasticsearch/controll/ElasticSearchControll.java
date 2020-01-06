package net.hycollege.protal.elasticsearch.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.protal.elasticsearch.service.ProductSearchService;

@RestController
@RequestMapping(value = "es")
public class ElasticSearchControll {
	@Autowired
	private ProductSearchService productSearchService;
	@GetMapping("/product/search")
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		return productSearchService.searchProduct(productName, pageIndex, size);
	}
	@PostMapping("/product/add")
	public void addProduct2Elasticsearch(@RequestBody ESProduct esProduct) {
		productSearchService.addProduct2Elasticsearch(esProduct);
	}

	@PostMapping("/product/update")
	public void updateProduct2Elasticsearch(@RequestBody ESProduct esProduct) {
		productSearchService.updateProduct2Elasticsearch(esProduct);
	}

	@PostMapping("/product/delete")
	public void deleteDocument(String productID) {
		productSearchService.deleteDocument(productID);
	}
	@PostMapping("/product/update/status")
	public void updateOneProductStatus2ElasticSearch(String productID, String status) {
		productSearchService.updateOneProductStatus2ElasticSearch(productID, status);
	}
}
