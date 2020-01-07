package net.hycollege.portal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.portal.filter.LoginInterceptor;

@Configuration
public class ProtalConfiguration/* implements WebMvcConfigurer */ {
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Bean
	public MappedInterceptor getMappedInterceptor() {
		return new MappedInterceptor(new String[] { "/**" },
				new String[] { "/login.html","/register.html", "/css/**", "/fonts/**", "/images/**", "/js/**", "/user/login" ,"/login", "/user/register"},
				loginInterceptor);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效

	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(loginInterceptor).addPathPatterns("/**").
	 * excludePathPatterns("/login.html", "/css/**", "/fonts/**", "/images/**",
	 * "/js/**", "/register.html", "/user/register", "/user/login","/index.html"); }
	 */
}
