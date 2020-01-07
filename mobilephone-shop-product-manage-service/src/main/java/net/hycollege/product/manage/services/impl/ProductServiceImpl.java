package net.hycollege.product.manage.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.message.bean.layui.LayUITableMessage;
import net.hycollege.message.bean.product.ProductMessage;
import net.hycollege.message.bean.product.ProductMessageAndESProduct;
import net.hycollege.message.bean.product.ShopCarMessage;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.ProductDesc;
import net.hycollege.mybatis.domain.ProductDescExample;
import net.hycollege.mybatis.domain.ProductExample;
import net.hycollege.mybatis.domain.UserCar;
import net.hycollege.mybatis.domain.UserCarExample;
import net.hycollege.product.manage.mapper.ProductDescMapper;
import net.hycollege.product.manage.mapper.ProductMapper;
import net.hycollege.product.manage.mapper.UserCarMapper;
import net.hycollege.product.manage.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductDescMapper productDescMapper;
	@Autowired
	private UserCarMapper userCarMapper;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addProduct(ProductMessage productMessage, ESProduct esProduct) {
		String uuid = UUID.randomUUID().toString();
		Product product = productMessage.getProduct();
		product.setPid(uuid);
		esProduct.setPid(uuid);
		esProduct.setStatus("1");
		product.setStatus("1");
		productMapper.insertSelective(product);
		ProductDesc productDesc = productMessage.getProductDesc();
		productDesc.setPdid(uuid);
		productDescMapper.insert(productDesc);
		addProduct2Elasticsearch(esProduct);
	}

	@Override
	public List<Product> selectProduct(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		ProductExample example = new ProductExample();
		return productMapper.selectByExample(example);
	}

	@Override
	public long selectAllProductsConunt() {
		return productMapper.countByExample(new ProductExample());
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ProductMessage selectOneProduct(String productID) {
		Product product = productMapper.selectByPrimaryKey(productID);
		ProductDesc productDesc = productDescMapper.selectByPrimaryKey(productID);
		return new ProductMessage(product, productDesc);
	}

	@Override
	public void deleteOneProduct(String productID) {
		productDescMapper.deleteByPrimaryKey(productID);
		productMapper.deleteByPrimaryKey(productID);
		try {
			deleteDocument(productID);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateOneProduct(ProductMessage productMessage, ESProduct esProduct) {
		Product product = productMessage.getProduct();
		String pid = product.getPid();
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andPidEqualTo(pid);
		productMapper.updateByExampleSelective(product, productExample);
		ProductDesc productDesc = productMessage.getProductDesc();
		ProductDescExample productDescExample = new ProductDescExample();
		productDescExample.createCriteria().andPdidEqualTo(pid);
		productDescMapper.updateByExampleSelective(productDesc, productDescExample);
		updateProduct2Elasticsearch(esProduct);
	}

	@Override
	public void addProduct2Elasticsearch(ESProduct esProduct) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = null;
		try {
			entity = new HttpEntity<String>(objectMapper.writeValueAsString(esProduct), headers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		restTemplate.postForObject("http://47.103.217.104:8085/es/product/add", entity, Void.class);
	}

	public void updateProduct2Elasticsearch(ESProduct esProduct) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = null;
		try {
			entity = new HttpEntity<String>(objectMapper.writeValueAsString(esProduct), headers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		restTemplate.postForObject("http://47.103.217.104:8085/es/product/update", entity, Void.class);
	}

	private void deleteDocument(String productID) throws IOException {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("productID", productID);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);
			restTemplate.postForObject("http://47.103.217.104:8085/es/product/delete", requestEntity, Void.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateOneProductStatus(String productID, String status) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("productID", productID);
			postParameters.add("status", status);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);
			restTemplate.postForObject("http://47.103.217.104:8085/es/product/update/status", requestEntity, Void.class);
			Product product = new Product();
			product.setPid(productID);
			product.setStatus(status);
			productMapper.updateByPrimaryKeySelective(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message addProduct2UserCar(String pid, String uid, int pnumber) {
		try {
			UserCar userCar = new UserCar();
			userCar.setCid(UUID.randomUUID().toString());
			userCar.setPid(pid);
			userCar.setUid(uid);
			userCar.setPnumber(pnumber);
			userCarMapper.insertSelective(userCar);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message(Message.ok, "1");
	}

	@Override
	public Message deleteProduct2UserCar(String cid) {
		try {
			userCarMapper.deleteByPrimaryKey(cid);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message();
	}

	@Override
	public Message updateProduct2UserCarPnumber(String cid, int pnumber) {
		try {
			UserCar userCar = new UserCar();
			userCar.setCid(cid);
			userCar.setPnumber(pnumber);
			userCarMapper.updateByPrimaryKeySelective(userCar);
		} catch (Exception e) {
			return new Message(Message.fail);
		}
		return new Message();
	}

	@Override
	public LayUITableMessage selectProduct2UserCar(String uid) {
		LayUITableMessage layUITableMessage = null;
		try {
			layUITableMessage = new LayUITableMessage();
			UserCarExample example = new UserCarExample();
			example.createCriteria().andUidEqualTo(uid);
			ArrayList<ShopCarMessage> arrayList = new ArrayList<ShopCarMessage>();
			List<UserCar> list = userCarMapper.selectByExample(example);
			ShopCarMessage shopCarMessage = null;
			for (UserCar userCar : list) {
				Product product = productMapper.selectByPrimaryKey(userCar.getPid());
				String product_json = objectMapper.writeValueAsString(product);
				shopCarMessage = objectMapper.readValue(product_json, ShopCarMessage.class);
				shopCarMessage.setPnumber(userCar.getPnumber());
				shopCarMessage.setCid(userCar.getCid());
				arrayList.add(shopCarMessage);
			}
			layUITableMessage.setData(arrayList);
		} catch (Exception e) {
			layUITableMessage.setCode(1);
			return layUITableMessage;
		}
		return layUITableMessage;
	}

	@Override
	public Message selectProduct2UserCarExist(String pid, String uid) {
		UserCarExample example = new UserCarExample();
		example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
		List<UserCar> list = userCarMapper.selectByExample(example);
		if (list.size() > 0) {
			return new Message(Message.ok, "1");
		}
		return new Message(Message.ok, "0");
	}

	@Override
	public Message deleteProduct2UserCarOnPid(String uid, String pid) {
		UserCarExample example = new UserCarExample();
		example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);
		userCarMapper.deleteByExample(example);
		return new Message(Message.ok);
	}

}
