package net.hycollege.services.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;

import net.hycollege.mapper.ProductDescMapper;
import net.hycollege.mapper.ProductMapper;
import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.ProductMessage;
import net.hycollege.message.utils.CheckObjects;
import net.hycollege.mybatis.domain.Product;
import net.hycollege.mybatis.domain.ProductDesc;
import net.hycollege.mybatis.domain.ProductDescExample;
import net.hycollege.mybatis.domain.ProductExample;
import net.hycollege.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductDescMapper productDescMapper;
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private ObjectMapper objectMapper;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addProduct(ProductMessage productMessage, ESProduct esProduct) {
		String uuid = UUID.randomUUID().toString();
		Product product = productMessage.getProduct();
		product.setPid(uuid);
		productMapper.insert(product);
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
		productMapper.updateByExampleSelective(product, productExample );
		ProductDesc productDesc = productMessage.getProductDesc();
		ProductDescExample productDescExample = new ProductDescExample();
		productDescExample.createCriteria().andPdidEqualTo(pid);
		productDescMapper.updateByExampleSelective(productDesc, productDescExample);
		updateProduct2Elasticsearch(esProduct);
	}

	@Override
	public void addProduct2Elasticsearch(ESProduct esProduct) {
		try {
			GetIndexRequest getIndexRequest = new  GetIndexRequest("product");
			boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			if(!exists) {
				IndexRequest indexRequest = new IndexRequest("product");
				restHighLevelClient.index(indexRequest , RequestOptions.DEFAULT);
				CreateIndexRequest request = new CreateIndexRequest("spring");//创建索引
				  //创建索引时创建文档类型映射
				String source="{\n" + 
						"	\"properties\": {\n" + 
						"	    \"pdesc\": {\n" + 
						"	        \"type\": \"text\",\n" + 
						"	        \"analyzer\": \"ik_max_word\",\n" + 
						"	        \"search_analyzer\": \"ik_smart\"\n" + 
						"	    },\n" + 
						"	    \"pname\": {\n" + 
						"	        \"type\": \"text\",\n" + 
						"	        \"analyzer\": \"ik_max_word\",\n" + 
						"	        \"search_analyzer\": \"ik_smart\"\n" + 
						"	    }\n" + 
						"	}\n" + 
						"}";
				request.mapping(source, XContentType.JSON);
		        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
			}
			
			IndexRequest indexRequest = new IndexRequest("product", "_doc", esProduct.getPid());
			indexRequest.source(objectMapper.writeValueAsString(esProduct));
			restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	public void updateProduct2Elasticsearch(ESProduct esProduct) {
		try {
			deleteDocument(esProduct.getPid());
			addProduct2Elasticsearch(esProduct);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteDocument(String productID) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest("product", "_doc", productID);
		restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
	}

	@Override
	public void updateOneProductStatus(String productID, String status) {
		UpdateRequest updateRequest = new UpdateRequest("product", "_doc", productID);
		Map<String, String> map = new HashMap<>();
		map.put("status", status);
		updateRequest.fetchSource(true);
		try {
			updateRequest.doc(objectMapper.writeValueAsString(map), XContentType.JSON);
			restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
			Product product = new Product();
			product.setPid(productID);
			product.setStatus(status);
			productMapper.updateByPrimaryKeySelective(product );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
