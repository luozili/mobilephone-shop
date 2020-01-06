package net.hycollege.protal.elasticsearch.service;

import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.elasticsearch.ESProduct;

public interface ProductSearchService {

	public ESMssage searchProduct(String productName, int pageIndex, int size);
	public void addProduct2Elasticsearch(ESProduct esProduct);
	public void updateProduct2Elasticsearch(ESProduct esProduct);
	public void deleteDocument(String productID);
	public void updateOneProductStatus2ElasticSearch(String productID, String status);
}
