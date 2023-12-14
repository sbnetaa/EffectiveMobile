package ru.terentyev.EffectiveMobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ru.terentyev.EffectiveMobile.entities.Person;
import ru.terentyev.EffectiveMobile.exceptions.PersonNotFoundException;
import ru.terentyev.EffectiveMobile.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	public Page<Person> findAll(int page){
		return personRepository.findAll(PageRequest.of(page, 20, Sort.by("id")));
	}
	
	public Person findById(long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("User with this ID not found"));
	}
}
