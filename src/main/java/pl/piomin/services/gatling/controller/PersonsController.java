package pl.piomin.services.gatling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.gatling.model.Person;
import pl.piomin.services.gatling.repository.PersonsRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonsController.class);
	
	@Autowired
	PersonsRepository repository;
	
	@GetMapping
	public List<Person> findAll() {
		return (List<Person>) repository.findAll();
	}
	
	@PostMapping
	public Person add(@RequestBody Person person) {
		Person p = repository.save(person);
		LOGGER.info("add: {}", p.toString());
		return p;
	}
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable("id") Long id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		repository.deleteById(id);
		LOGGER.info("deleteById: id={}", id);
	}

	@DeleteMapping
	public void delete(@RequestBody Person person) {
		repository.delete(person);
		LOGGER.info("delete: {}", person.toString());
	}
	
}
