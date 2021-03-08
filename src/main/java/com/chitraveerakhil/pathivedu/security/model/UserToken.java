package com.chitraveerakhil.pathivedu.security.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserToken {
	
	private Long id;
	private String token;
	private String username;
	private String email;
	private List<String> role;
	
}

