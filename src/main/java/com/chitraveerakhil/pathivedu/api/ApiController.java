package com.chitraveerakhil.pathivedu.api;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
	ApiService persons = new ApiService();

	@GetMapping("/hello")
	public User user() throws NoSuchAlgorithmException, InvalidKeySpecException {
		return persons.user();
	}

}
