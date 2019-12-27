package com.chitraveerakhil.pathivedu.cache.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.cache.service.CacheService;
import com.chitraveerakhil.pathivedu.vo.LeaveVo;

@Component("leaveCacheService")
public class LeaveCacheService implements CacheService<LeaveVo> {

	private static final String CACHE_PREFIX = "leaveCachePrefix";

	public static String getCacheKey(String relevant) {
		return CACHE_PREFIX + relevant;
	}

	@Override
	@Cacheable(cacheNames = "leaveCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService).getCacheKey(#id)")
	public LeaveVo getFromCache(long id) {
		return null;
	}

	@CacheEvict(cacheNames = "leaveCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService).getCacheKey(#id)")
	public void removeFromCache(String relevant) {
	}

	@Override
	@CachePut(cacheNames = "leaveCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService).getCacheKey(#id)")
	public LeaveVo populateCache(LeaveVo cache, long id) {
		return cache;
	}

	@Override
	public List<LeaveVo> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromCache(long id) {
	}

	@CachePut(cacheNames = "leaveListCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService).getCacheKey(#userId)")
	public List<LeaveVo> getListByUser(long userId) {
		return new ArrayList<>();
	}

	@CachePut(cacheNames = "leaveListCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService).getCacheKey(#userId)")
	public List<LeaveVo> populateCacheByUser(List<LeaveVo> cache, long userId) {
		return cache;
	}

}
