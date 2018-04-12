package com.bicjo.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@SuppressWarnings("serial")
	@GetMapping(value = "/world", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Map<String, String> world() {
		return new HashMap<String, String>() {
			{
				put("msg", "Hello World");
			}
		};
	}

}
