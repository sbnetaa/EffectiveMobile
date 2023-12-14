package ru.terentyev.EffectiveMobile.services;

import org.springframework.data.domain.Page;

import ru.terentyev.EffectiveMobile.entities.Person;

public interface PersonService {
	Page<Person> findAll(int page);
	Person findById(long id);
}
