package com.example.hrms.cache;

import java.net.URL;
import java.util.Collections;
import javax.cache.Caching;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {


  @Primary
  @Bean
  public CacheManager redisCacheManager() {
      return RedisCacheManager.builder(redisConnectionFactory()).build();
  }
  
 
  
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
	  return new LettuceConnectionFactory("localhost", 6379);
  }

	
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
	    redisTemplate.setConnectionFactory(redisConnectionFactory);
	    return redisTemplate;
	}

  @Bean
  public JCacheCacheManager jCacheCacheManager() {
    return new JCacheCacheManager(ehCacheManager());
  }

  @Bean
  public javax.cache.CacheManager ehCacheManager() {
    URL myUrl = getClass().getResource("/ehcache.xml");
    org.ehcache.config.Configuration xmlConfig = new XmlConfiguration(myUrl);
    EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
    return provider.getCacheManager(provider.getDefaultURI(),  xmlConfig);
  }

  @Bean
  public CacheResolver cacheResolver() {
    return new CustomCacheResolver(jCacheCacheManager(), redisCacheManager());
  }
  
  

}
