package com.bicjo.sample.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
@ExposesResourceFor(User.class)
public class UserController {

	private final EntityLinks entityLinks;

	public UserController(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Resources<UserResource>> getUsers() {

		List<ContactResource> contactResources = new ArrayList<>();
		contactResources.add(new ContactResource("1234567890"));
		contactResources.add(new ContactResource("0987654321"));

		List<UserResource> userResources = new ArrayList<>();
		userResources.add(new UserResource("Joven", new Resources<>(contactResources)));
		userResources.add(new UserResource("Rose", new Resources<>(contactResources)));
		userResources.add(new UserResource("Chance", new Resources<>(contactResources)));

		Resources<UserResource> resources = new Resources<>(userResources);

		resources.add(entityLinks.linkToCollectionResource(User.class));

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@GetMapping(value = "/{uid}", //
			produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" } //
	)
	public ResponseEntity<UserResource> getUser(@PathVariable(name = "uid") String userId) {

		UserResource resource = new UserResource("Joven", new Resources<>(Collections.emptyList()));
		resource.add(//
				entityLinks.linkToSingleResource(User.class, userId) //
		);

		return ResponseEntity.ok(resource);
	}

}
