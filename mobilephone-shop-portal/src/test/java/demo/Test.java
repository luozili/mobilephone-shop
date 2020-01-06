package demo;

import java.io.IOException;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hycollege.message.bean.elasticsearch.ESProduct;

public class Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ESProduct readValue = objectMapper.readValue("{\"pname\":\"iphone18\",\"pid\":\"0f5cf0de-2595-4c9c-8e89-0fb0fc06717b\",\"prices\":88888.0,\"picture\":\"http://47.103.217.104:8889/group1/M00/00/00/L2fZaF4DEMOADaLQAABNJG1pOjM309.jpg\"}", ESProduct.class);
		System.out.println(readValue.getPid());
	}

}
