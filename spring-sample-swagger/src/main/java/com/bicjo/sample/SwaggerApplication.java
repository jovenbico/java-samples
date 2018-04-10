package com.bicjo.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

	@Bean
	public Docket myDocket() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.useDefaultResponseMessages(false)//
				.apiInfo(apiInfo())//
				.select()//
				.paths(Predicates.not(PathSelectors.regex("/error.*")))//
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Pet API")//
				.description("description")//
				.contact(new Contact("name", "url", "email"))//
				.license("Apache License Version 2.0")//
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")//
				.version("2.0")//
				.build();
	}

}

@RestController
@RequestMapping(value = "/pets", //
		produces = { MediaType.APPLICATION_JSON_VALUE } //
)
class PetController {

	@ApiOperation(value = "Find pet by ID", notes = "Returns a pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "OK", response = Pet.class), //
			@ApiResponse(code = 400, message = "Invalid ID supplied")//
	})

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pet> getById(//
			@PathVariable long id//
	) {
		return ResponseEntity.ok(new Pet(100L, "pet-100"));
	}

}

class Pet {

	private Long id;
	private String name;

	public Pet() {
		this(null, null);
	}

	public Pet(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}