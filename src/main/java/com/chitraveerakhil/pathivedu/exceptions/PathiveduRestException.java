package com.chitraveerakhil.pathivedu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class PathiveduRestException extends HttpServerErrorException {

	private static final long serialVersionUID = 8368746963476597995L;

	public PathiveduRestException(int statusCode) {
		super(HttpStatus.valueOf(statusCode));
	}

	public PathiveduRestException(int statusCode, String statusText) {
		super(HttpStatus.valueOf(statusCode), statusText);
	}

}
