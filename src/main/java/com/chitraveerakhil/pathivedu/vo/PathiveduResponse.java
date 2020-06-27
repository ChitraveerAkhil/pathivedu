package com.chitraveerakhil.pathivedu.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PathiveduResponse<T> {

	private String status = "success";
	private String message = "Response returned successfully";
	private String code = "200";
	private T data = null;

	public PathiveduResponse(T data) {
		this.data = data;
	}

	public PathiveduResponse(String msg) {
		this.message = msg;
	}

	public PathiveduResponse(T data, String msg) {
		this.message = msg;
		this.data = data;
	}

	public PathiveduResponse(String msg, String code) {
		this.message = msg;
		this.code = code;
	}
}
