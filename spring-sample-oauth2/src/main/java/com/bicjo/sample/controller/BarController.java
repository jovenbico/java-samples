package com.bicjo.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bar")
public class BarController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "/login")
	public String login() {

		LOG.debug(">>> bar-login <<<");

		return "bar/login";
	}

	@GetMapping(value = "/hello/world")
	public String hello() {
		return "bar/hello";
	}
}
