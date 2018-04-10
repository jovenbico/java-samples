package com.bicjo.sample;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.Data;

@SpringBootApplication
public class SpringSampleMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSampleMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(PersonRepository personRepository) {

		return args -> {
			if (personRepository.count() == 0) {
				personRepository.save(new Person("Joven", "Bico"));
				personRepository.save(new Person("Rose", "Bico"));
				personRepository.save(new Person("Chance", "Bico"));
			}
		};

	}

}

@Data
@Document
class Person {
	@Id
	private String id;
	private String firstname;
	private String lastname;

	public Person() {
	}

	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
}

@RepositoryRestResource
interface PersonRepository extends CrudRepository<Person, String> {

	List<Person> findByFirstname(@Param("fname") String firstname);

	List<Person> findByLastname(@Param("lname") String lastname);
}
