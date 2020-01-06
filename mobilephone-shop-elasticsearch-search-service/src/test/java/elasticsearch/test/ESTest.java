package elasticsearch.test;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.elasticsearch.ESProduct;

class ESTest {

	@Test
	void test() {
		ESProduct esProduct = new ESProduct("1231234", "http://kdawj/xx.jpg", "罗子利", "1", 12.0f, "");
		ObjectMapper objectMapper = new ObjectMapper();
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost("47.103.217.104", 9200)));
		try {
			GetIndexRequest getIndexRequest = new GetIndexRequest("index");
			boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			if (!exists) {
				CreateIndexRequest request = new CreateIndexRequest("index");// 创建索引
				// 创建索引时创建文档类型映射
				//A specialized simplified mapping source method, takes the form of simple properties definition: ("field1", "type=string,store=true").
				String source = "{\"properties\":{\"content\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"}}}";
				request.mapping("text", source, XContentType.JSON);
				restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
			}

			IndexRequest indexRequest = new IndexRequest("index", "_doc", "2");
			indexRequest.source("{\"content\":\"中韩渔警冲突调查：韩警平均每天扣1艘中国渔船\"}", XContentType.JSON);
			restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
