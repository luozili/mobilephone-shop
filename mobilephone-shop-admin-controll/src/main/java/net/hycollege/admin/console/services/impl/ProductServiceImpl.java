package net.hycollege.admin.console.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
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

import net.hycollege.admin.console.services.ProductService;
import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.message.bean.product.ProductMessageAndESProduct;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.ProductDesc;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addProduct(Product product, String pdesc, ESProduct esProduct) {
		ProductDesc productDesc = new ProductDesc();
		productDesc.setPdesc(pdesc);

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			ProductMessage productMessage = new ProductMessage(product, productDesc);
			ProductMessageAndESProduct productMessageAndESProduct = new ProductMessageAndESProduct(productMessage,
					esProduct);
			HttpEntity<String> requestEntity = new HttpEntity<>(
					objectMapper.writeValueAsString(productMessageAndESProduct), headers);
			restTemplate.postForObject("http://193.112.173.11:8084/product/add", requestEntity, Message.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public ProductMessage selectOneProduct(String productID) {
		try {
			return restTemplate.getForObject("http://193.112.173.11:8084/product/get?productID=" + productID,
					ProductMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Message deleteOneProduct(String productID) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("productID", productID);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);

			return restTemplate.postForObject("http://193.112.173.11:8084/product/delete", requestEntity,
					Message.class);
		} catch (Exception e) {
			System.out.println(e);
			return new Message(Message.fail);
		}
	}

	@Override
	public Message updateOneProduct(ProductMessage productMessage, ESProduct esProduct) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			ProductMessageAndESProduct productMessageAndESProduct = new ProductMessageAndESProduct(productMessage,
					esProduct);
			HttpEntity<String> requestEntity = new HttpEntity<String>(
					objectMapper.writeValueAsString(productMessageAndESProduct), headers);
			return restTemplate.postForObject("http://193.112.173.11:8084/product/update", requestEntity,
					Message.class);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return new Message(Message.fail);
		}
	}

	@Override
	public Message updateOneProductStatus(String productID, String status) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("productID", productID);
			postParameters.add("status", status);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);
			return restTemplate.postForObject("http://193.112.173.11:8084/product/update/status", requestEntity,
					Message.class);
		} catch (Exception e) {
			System.out.println(e);
			return new Message(Message.fail);
		}
	}

	@Override
	public LayUITableMessage getProducts(int currentPage, int pageSize) {
		try {
			return restTemplate.getForObject(
					"http://193.112.173.11:8084/product/gets?currentPage=" + currentPage + "&pageSize=1" + pageSize,
					LayUITableMessage.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
