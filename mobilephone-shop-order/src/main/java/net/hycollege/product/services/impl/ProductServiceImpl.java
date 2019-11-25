package net.hycollege.product.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.LayUITableMessage;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.ProductMessage;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.ProductDesc;
import net.hycollege.product.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;


	@Override
	public ProductMessage selectOneProduct(String productID) {
		try {
			return restTemplate.getForObject("http://localhost:8084/product/get?productID=" + productID,
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
					"http://localhost:8084/product/gets?currentPage=" + currentPage + "&pageSize=1" + pageSize,
					LayUITableMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
