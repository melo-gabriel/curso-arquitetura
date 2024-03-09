package br.com.byteup.hellodocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.byteup.hellodocker.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	

}
