package net.hycollege.admin.console.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import net.hycollege.admin.console.filter.LoginInterceptor;

@Configuration
public class WebConfiguration /* implements WebMvcConfigurer */{
	@Autowired
	private LoginInterceptor loginInterceptor;

	
	  @Bean public MappedInterceptor getMappedInterceptor() { return new
	  MappedInterceptor(new String[] { "/**" }, new String[] { "/login.html",
	  "/css/**", "/images/**", "/js/**", "/admin/login", "/admin/login" }, loginInterceptor); }
	 

	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(loginInterceptor).addPathPatterns("/**").
	 * excludePathPatterns("/login.html", "/css/**", "/images/**", "/js/**",
	 * "/admin/login"); }
	 */

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	@Bean
	public RestHighLevelClient restHighLevelClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
	}
}
