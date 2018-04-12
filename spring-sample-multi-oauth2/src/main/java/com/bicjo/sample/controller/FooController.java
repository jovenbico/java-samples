package com.bicjo.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/login")
	public String login() {

		LOG.debug(">>> foo-login <<<");

		return "foo/login";
	}

}