package net.hycollege.protal.elasticsearch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.protal.elasticsearch.dao.SearchProductDao;
import net.hycollege.protal.elasticsearch.service.ProductSearchService;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
	@Autowired
	private SearchProductDao searchProductDao;

	@Override
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		return searchProductDao.searchProduct(productName, pageIndex, size);
	}

	@Override
	public void addProduct2Elasticsearch(ESProduct esProduct) {
		searchProductDao.addProduct2Elasticsearch(esProduct);
	}

	@Override
	public void updateProduct2Elasticsearch(ESProduct esProduct) {
		searchProductDao.updateProduct2Elasticsearch(esProduct);

	}

	@Override
	public void deleteDocument(String productID) {
		searchProductDao.deleteDocument(productID);

	}

	@Override
	public void updateOneProductStatus2ElasticSearch(String productID, String status) {
		searchProductDao.updateOneProductStatus2ElasticSearch(productID, status);
	}

}
