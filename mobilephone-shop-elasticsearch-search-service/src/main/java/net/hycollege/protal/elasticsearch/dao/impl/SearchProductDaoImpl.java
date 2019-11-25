package net.hycollege.protal.elasticsearch.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.ESMssage;
import net.hycollege.message.bean.ESProduct;
import net.hycollege.message.bean.Message;
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
		searchRequest.types("_doc");
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
				ESProduct esProduct = objectMapper.convertValue(hit.getSourceAsString(), ESProduct.class);
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

}
