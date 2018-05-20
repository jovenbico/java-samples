package com.bicjo.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HateoasApplicationTests {

	private final Logger LOG = Logger.getLogger(getClass());

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUsers() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("/users", String.class);

		LOG.info(entity.getBody());
	}

	@Test
	public void getUserById() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity("/users/1", String.class);

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

		LOG.info(entity.getBody());
	}
}
