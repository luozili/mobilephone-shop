package net.hycollege.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ElasticSearchConfiguration {
	@Bean
	public RestHighLevelClient restHighLevelClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost("47.103.217.104", 9200)));
	}
}
