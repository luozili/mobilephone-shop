package net.hycollege.protal.elasticsearch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.hycollege.message.bean.ESMssage;
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

}
