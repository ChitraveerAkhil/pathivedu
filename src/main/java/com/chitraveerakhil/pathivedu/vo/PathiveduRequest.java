package com.chitraveerakhil.pathivedu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathiveduRequest<T> {

	private long requestorId;
	private T data;

	public PathiveduRequest(T data) {
		this.data = data;
	}
}
