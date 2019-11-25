package net.hycollege.protal.elasticsearch.service;

import net.hycollege.message.bean.ESMssage;

public interface ProductSearchService {

	public ESMssage searchProduct(String productName, int pageIndex, int size);
}
