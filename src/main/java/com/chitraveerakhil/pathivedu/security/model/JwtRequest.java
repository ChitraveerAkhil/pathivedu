package com.chitraveerakhil.pathivedu.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
	private String username;
	private String password;
}
