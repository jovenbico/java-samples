package com.bicjo.sample.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resources")
public class ResourceController {

	@GetMapping
	public Map<String, Object> get(Authentication auth) {
		OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
		Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
		System.out.println("User organization is " + details);
		return details;
	}

}
