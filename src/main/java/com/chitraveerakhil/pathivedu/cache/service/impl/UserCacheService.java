package com.chitraveerakhil.pathivedu.cache.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.cache.service.CacheService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;

@Component("userCacheService")
public class UserCacheService implements CacheService<UserProfile> {

	private static final String CACHE_PREFIX = "userCachePrefix";

	public static String getCacheKey(String relevant) {
		return CACHE_PREFIX + relevant;
	}

	@Override
	@Cacheable(cacheNames = "userCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.UserCacheService).getCacheKey(#id)")
	public UserProfile getFromCache(long id) {
		return null;
	}

	@CacheEvict(cacheNames = "userCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.UserCacheService).getCacheKey(#id)")
	public void removeFromCache(String relevant) {
	}

	@Override
	@CachePut(cacheNames = "userCache", key = "T(com.chitraveerakhil.pathivedu.cache.service.impl.UserCacheService).getCacheKey(#id)")
	public UserProfile populateCache(UserProfile cache, long id) {
		return cache;
	}

	@Override
	public List<UserProfile> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromCache(long id) {
	}

}
