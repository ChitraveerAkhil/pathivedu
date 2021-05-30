package com.chitraveerakhil.pathivedu.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;

public class PathiveduCachingException extends RedisConnectionFailureException {

	Logger Log = LoggerFactory.getLogger(PathiveduCachingException.class);

	public PathiveduCachingException(String msg, Throwable e) {
		super(msg, e);
		Log.warn("Redis server is not connected for caching " + e.getLocalizedMessage());
	}

	private static final long serialVersionUID = -1684696675038568502L;

}
