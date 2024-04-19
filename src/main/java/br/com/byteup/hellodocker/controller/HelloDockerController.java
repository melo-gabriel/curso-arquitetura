package br.com.byteup.hellodocker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.byteup.hellodocker.model.Person;
import br.com.byteup.hellodocker.repository.PersonRepository;

@RequestMapping("docker")
@RestController
public class HelloDockerController {
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public String helloDocker() {
		return "Hello Docker";
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		personRepository.save(person);		
		return ResponseEntity.status(HttpStatus.CREATED).body(person);
	}

	@GetMapping("/pessoas")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }
}
