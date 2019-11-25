package net.hycollege.protal.elasticsearch.dao;


import net.hycollege.message.bean.ESMssage;

public interface SearchProductDao {

	public ESMssage searchProduct(String productName, int pageIndex, int size);
}
