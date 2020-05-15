package net.hycollege.protal.elasticsearch.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.Message;
import net.hycollege.message.bean.elasticsearch.ESMssage;
import net.hycollege.message.bean.elasticsearch.ESProduct;
import net.hycollege.protal.elasticsearch.dao.SearchProductDao;

@Repository
public class SearchProductDaoImpl implements SearchProductDao {
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ESMssage searchProduct(String productName, int pageIndex, int size) {
		List<ESProduct> list = new ArrayList<ESProduct>();
		SearchRequest searchRequest = new SearchRequest("product");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.from(pageIndex * size);
		searchSourceBuilder.size(size);
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		boolQuery.must(QueryBuilders.matchQuery("pname", productName));
		boolQuery.must(QueryBuilders.matchQuery("pdesc", productName));
		boolQuery.must(QueryBuilders.matchQuery("status", "1"));
		searchSourceBuilder.query(boolQuery);
		String[] includeFields = new String[] { "pid", "picture", "pname", "prices"  };
		String[] excludeFields = new String[] { "status", "pdesc"};
		searchSourceBuilder.fetchSource(includeFields, excludeFields);
		searchRequest.source(searchSourceBuilder);
		ESMssage esMssage = new ESMssage();
		try {

			SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			long TotalValue = hits.getTotalHits().value;
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) {
//				ESProduct esProduct = objectMapper.convertValue(hit.getSourceAsString(), ESProduct.class);
				ESProduct esProduct = objectMapper.readValue(hit.getSourceAsString(), ESProduct.class);
				list.add(esProduct);
			}
			esMssage.setTotalCount(TotalValue);
			esMssage.setMessage(list);
			esMssage.setStatus(Message.ok);
			return esMssage;
		} catch (IOException e) {
			e.printStackTrace();
			esMssage.setStatus(Message.fail);
			return esMssage;
		}

	}
	@Override
	public void addProduct2Elasticsearch(ESProduct esProduct) {
		try {
			GetIndexRequest getIndexRequest = new  GetIndexRequest("product");
			boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			if(!exists) {
				CreateIndexRequest request = new CreateIndexRequest("product");//创建索引
				// 创建索引时创建文档类型映射
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
				request.mapping("text", source, XContentType.JSON);
		        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
			}
			
			IndexRequest indexRequest = new IndexRequest("product", "_doc", esProduct.getPid());
			indexRequest.source(objectMapper.writeValueAsString(esProduct), XContentType.JSON);
			restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	@Override
	public void updateProduct2Elasticsearch(ESProduct esProduct) {
			deleteDocument(esProduct.getPid());
			addProduct2Elasticsearch(esProduct);
			System.out.println("update success on es");
	}
	@Override
	public void deleteDocument(String productID) {
		DeleteRequest deleteRequest = new DeleteRequest("product", "_doc", productID);
		try {
			restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateOneProductStatus2ElasticSearch(String productID, String status) {
		UpdateRequest updateRequest = new UpdateRequest("product", "_doc", productID);
		Map<String, String> map = new HashMap<>();
		map.put("status", status);
		updateRequest.fetchSource(true);
		try {
			updateRequest.doc(objectMapper.writeValueAsString(map), XContentType.JSON);
			restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
