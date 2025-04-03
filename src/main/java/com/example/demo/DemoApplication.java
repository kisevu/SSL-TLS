package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping("/register")
	public ResponseEntity<?> registerPerson(@RequestBody PersonRequest request){
		Person person = Person.builder()
				.personId(1L)
				.name(request.name())
				.age(request.age())
				.timeRegistered(LocalDateTime.now())
				.build();

		 String query = "INSERT INTO person (person_id,name, age,time_registered) VALUES(?,?,?,?)";
		int update = jdbcTemplate.update(query, person.getPersonId(), person.getName(), person.getAge(), person.getTimeRegistered());
		return ResponseEntity.ok(update);
	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Person {
	@Id
	private Long personId;
	private String name;
	private Integer age;
	private LocalDateTime timeRegistered;
}

record  PersonRequest(String name, Integer age){}
