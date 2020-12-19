package com.chitraveerakhil.pathivedu.cache.service;

import java.util.List;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

@Service
public interface CacheService<T> {

	T getFromCache(long id) throws RedisConnectionFailureException;

	T populateCache(T cache, long id) throws RedisConnectionFailureException;

	List<T> getList() throws RedisConnectionFailureException;

	void removeFromCache(long id) throws RedisConnectionFailureException;

}
