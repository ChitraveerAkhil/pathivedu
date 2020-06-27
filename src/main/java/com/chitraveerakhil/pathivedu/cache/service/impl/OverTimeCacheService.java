package com.chitraveerakhil.pathivedu.cache.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.cache.service.CacheService;
import com.chitraveerakhil.pathivedu.vo.OverTimeVo;

@Component("overTimeCacheService")
public class OverTimeCacheService implements CacheService<OverTimeVo> {

	private static final String CACHE_PREFIX = "overTimeCachePrefix";

	public static String getCacheKey(String relevant) {
		return CACHE_PREFIX + relevant;
	}

	@Override
	@Cacheable(cacheNames = "overTimeCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.OverTimeCacheService).getCacheKey(#id)")
	public OverTimeVo getFromCache(long id) {
		return null;
	}

	@CacheEvict(cacheNames = "overTimeCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.OverTimeCacheService).getCacheKey(#id)")
	public void removeFromCache(String relevant) {
	}

	@Override
	@CachePut(cacheNames = "overTimeCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.OverTimeCacheService).getCacheKey(#id)")
	public OverTimeVo populateCache(OverTimeVo cache, long id) {
		return cache;
	}

	@Override
	public List<OverTimeVo> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromCache(long id) {
	}

	@CachePut(cacheNames = "overTimeListCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.OverTimeCacheService).getCacheKey(#userId)")
	public List<OverTimeVo> getListByUser(long userId) {
		return new ArrayList<>();
	}

	@CachePut(cacheNames = "overTimeListCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.OverTimeCacheService).getCacheKey(#userId)")
	public List<OverTimeVo> populateCacheByUser(List<OverTimeVo> cache, long userId) {
		return cache;
	}
}
