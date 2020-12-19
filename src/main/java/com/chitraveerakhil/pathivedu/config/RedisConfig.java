package com.chitraveerakhil.pathivedu.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Value("${spring.redis.cache.timeout}")
	private long timeout;

	private Map<String, Long> cacheExpirations = new HashMap<>();

	private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(timeoutInSeconds));
	}

	@Bean
	protected JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(conf, jedisClientConfiguration);

		factory.getPoolConfig().setMaxIdle(30);
		factory.getPoolConfig().setMinIdle(10);

		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return createCacheConfiguration(this.timeout);
	}

	@Bean
	public CacheManager cacheManager() {
		Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

		for (Entry<String, Long> cacheNameAndTimeout : this.cacheExpirations.entrySet()) {
			cacheConfigurations.put(cacheNameAndTimeout.getKey(),
					createCacheConfiguration(cacheNameAndTimeout.getValue()));
		}

		return RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration())
				.withInitialCacheConfigurations(cacheConfigurations).build();
	}

}
