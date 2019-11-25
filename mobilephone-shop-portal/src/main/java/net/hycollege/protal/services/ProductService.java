package net.hycollege.protal.services;

import net.hycollege.message.bean.ESMssage;

public interface ProductService {

	ESMssage searchProduct(String productName, int pageIndex, int size);

}
