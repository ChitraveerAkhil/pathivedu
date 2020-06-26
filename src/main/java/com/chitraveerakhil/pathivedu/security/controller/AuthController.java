package com.chitraveerakhil.pathivedu.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.helper.SecurePassword;
import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.security.config.AuthTokenUtil;
import com.chitraveerakhil.pathivedu.security.model.AuthUserDetails;
import com.chitraveerakhil.pathivedu.security.model.JwtRequest;
import com.chitraveerakhil.pathivedu.security.model.JwtResponse;
import com.chitraveerakhil.pathivedu.service.UserService;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenUtil jwtTokenUtil;

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authReq) throws Exception {

		User user = service.findByUsername(authReq.getUsername());
		Authentication authentication = authenticate(user, authReq.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenUtil.generateToken(authentication);

		AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(userDetails.getId(), token, userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	private Authentication authenticate(User user, String password) throws Exception {
		try {
			if (user != null) {
				boolean isValidate = SecurePassword.validatePassword(password, user.getSalt(), user.getHash(),
						user.getIterator());
				if (isValidate) {
					
					return authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(user.getPhoneNumber(), user.getHash()));
				} else {
					throw new Exception("Invalid Credentials");
				}
			} else {
				throw new Exception("User Not Found");
			}
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
