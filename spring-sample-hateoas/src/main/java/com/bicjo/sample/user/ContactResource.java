package com.bicjo.sample.user;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Relation(value = "contact", collectionRelation = "contacts")
public class ContactResource extends ResourceSupport {

	private String number;

}
