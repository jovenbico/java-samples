package com.bicjo.sample.user;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Relation(value = "user", collectionRelation = "users")
public class UserResource extends ResourceSupport {

	private String name;

	@JsonProperty(value = "contacts")
	private Resources<ContactResource> contactResources;

}
