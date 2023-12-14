package ru.terentyev.EffectiveMobile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.terentyev.EffectiveMobile.entities.Person;
import ru.terentyev.EffectiveMobile.repositories.PersonRepository;
import ru.terentyev.EffectiveMobile.security.PersonDetails;

@Service
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {
	
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PersonRepository personRepository;
    
    @Autowired
    public PersonDetailsService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
			PersonRepository personRepository) {
		super();	
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.personRepository = personRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String username) {    	
        Optional<Person> person = personRepository.findByName(username); 
            return new PersonDetails(person.orElseThrow(() -> new UsernameNotFoundException("Username not found")));
  
    }
    
    @Transactional(readOnly = false)
    public Person registerNewUserAccount(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }
    
    public Person findById(Long Id) {
    	Optional<Person> oPerson = personRepository.findById(Id); 
        return oPerson.orElse(null);    
    }
	
    public Person findByUsername(String name) {
    	Optional<Person> oPerson = personRepository.findByName(name);
    	return oPerson.orElse(null);
    	
    }
    
    public List<Person> findAll(){
    	return personRepository.findAll();
    }
}
