package com.chitraveerakhil.pathivedu.cache.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CacheService<T> {

	T getFromCache(long id);

	T populateCache(T cache, long id);

	List<T> getList();

	void removeFromCache(long id);

}
